package excute;

import java.util.Date;

public interface ExcuteIntf {
  /**
   * @Description: 执行某一天的所有批
   *
   * @param 执行日期
   * @return java.lang.Boolean
   * @throws
   * @author lijunyu
   * @date 2021/11/19 9:04 =========================================== 修改人：lijunyu 修改时间：2021/11/19
   *     9:04， 修改版本： 修改备注： ===========================================
   */
  Boolean excute(Date date);
  /**
   * @Description: 执行某天具体某一个批
   *
   * @param 执行日期，批处理代码
   * @return java.lang.Boolean
   * @throws
   * @author lijunyu
   * @date 2021/11/19 9:04 =========================================== 修改人：lijunyu 修改时间：2021/11/19
   *     9:04， 修改版本： 修改备注： ===========================================
   */
  Boolean excute(Date date, String jobcode);


}
