package com.lili.controllor.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.ExistsBuilder;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ZkTestController")
class ZkTestController {

    @Autowired
    CuratorFramework curatorFramework;

    /**
     * 创建节点
     *
     * @throws Exception
     */
    @RequestMapping("/createNode")
    public String createNode() throws Exception {
        // 添加持久节点
        ExistsBuilder existsBuilder = curatorFramework.checkExists();
        String path = curatorFramework.create().forPath("/curator-node");
        System.out.println(String.format("curator create node :%s successfully.", path));

        // 添加临时序号节点,并赋值数据
        String path1 = curatorFramework.create()
                .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                .forPath("/curator-node", "some-data".getBytes());
        System.out.println(String.format("curator create node :%s successfully.", path));

         return path1;
    }

    /**
     * 获取节点
     *
     * @throws Exception
     */
    @RequestMapping("/testGetData")
    public String testGetData() throws Exception {
        // 在上面的方法执行后，创建了curator-node节点，但是我们并没有显示的去赋值
        // 通过这个方法去获取节点的值会发现，当我们通过Java客户端创建节点不赋值的话默认就是存储的创建节点的ip
        byte[] bytes = curatorFramework.getData().forPath("/curator-node");
        return new String(bytes);
    }

    /**
     * 修改节点数据
     *
     * @throws Exception
     */
    @RequestMapping("/testSetData")
    public String testSetData() throws Exception {
        curatorFramework.setData().forPath("/curator-node", "changed!".getBytes());
        byte[] bytes = curatorFramework.getData().forPath("/curator-node");
        return new String(bytes);
    }

    /**
     * 创建节点同时创建⽗节点
     *
     * @throws Exception
     */
    @RequestMapping("/testCreateWithParent")
    public String testCreateWithParent() throws Exception {
        String pathWithParent = "/node-parent/sub-node-1";
        String path = curatorFramework.create().creatingParentsIfNeeded().forPath(pathWithParent);
        return String.format("curator create node :%s successfully.", path);
    }

    /**
     * 删除节点(包含子节点)
     *
     * @throws Exception
     */
    @RequestMapping("/testDelete")
    public void testDelete() throws Exception {
        String pathWithParent = "/curator-node";
        curatorFramework.delete().guaranteed().deletingChildrenIfNeeded().forPath(pathWithParent);
    }
}

