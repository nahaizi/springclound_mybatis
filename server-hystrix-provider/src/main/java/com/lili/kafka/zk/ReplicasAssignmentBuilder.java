package com.lili.kafka.zk;

import java.util.*;

public class ReplicasAssignmentBuilder {
    public static Map<String, List<Integer>> assignReplicasToBrokers(List<Integer> brokerListSet, int nPartitions, int nReplicationFactor) {
        return assignReplicasToBrokers(brokerListSet, nPartitions, nReplicationFactor, -1, -1);
    }

    /**
     * There are 2 goals of replica assignment:
     * 1. Spread the replicas evenly among brokers.
     * 2. For partitions assigned to a particular broker, their other replicas are spread over the other brokers.
     *
     * To achieve this goal, we:
     * 1. Assign the first replica of each partition by round-robin, starting from a random position in the broker list.
     * 2. Assign the remaining replicas of each partition with an increasing shift.
     *
     * Here is an example of assigning
     * broker-0  broker-1  broker-2  broker-3  broker-4
     * p0        p1        p2        p3        p4       (1st replica)
     * p5        p6        p7        p8        p9       (1st replica)
     * p4        p0        p1        p2        p3       (2nd replica)
     * p8        p9        p5        p6        p7       (2nd replica)
     * p3        p4        p0        p1        p2       (3nd replica)
     * p7        p8        p9        p5        p6       (3nd replica)
     */
    public static Map<String, List<Integer>> assignReplicasToBrokers(List<Integer> brokerListSet, int nPartitions, int nReplicationFactor,
                                                                     int fixedStartIndex, int startPartitionId) {
        // sort
        brokerListSet.sort(new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }

        });
        if (nPartitions <= 0) {
            throw new RuntimeException("num of partition cannot less than 1");
        }
        if (nReplicationFactor <= 0) {
            throw new RuntimeException("num of replication factor cannot less than 1");
        }
        if (nReplicationFactor > brokerListSet.size()) {
            throw new RuntimeException("broker num is less than replication factor num");
        }

        Random random = new Random();

        Map<String, List<Integer>> result = new HashMap<String, List<Integer>>();
        int startIndex = fixedStartIndex >= 0 ? fixedStartIndex : random.nextInt(brokerListSet.size());
        int currentPartitionId = startPartitionId >= 0 ? startPartitionId : 0;
        int nextReplicaShift = fixedStartIndex >= 0 ? fixedStartIndex : random.nextInt(brokerListSet.size());

        for (int i = 0; i < nPartitions; i++) {
            if (currentPartitionId > 0 && ((currentPartitionId % brokerListSet.size()) == 0)) {
                nextReplicaShift++;
            }
            int firstReplicaIndex = (currentPartitionId + startIndex) % brokerListSet.size();
            List<Integer> replicaList = new ArrayList<Integer>();
            replicaList.add(brokerListSet.get(firstReplicaIndex));
            for (int j = 0; j < (nReplicationFactor - 1); j++) {
                replicaList.add(brokerListSet.get(getReplicaIndex(firstReplicaIndex, nextReplicaShift, j, brokerListSet.size())));
            }
            result.put("" + currentPartitionId, getReverseList(replicaList));
            currentPartitionId++;
        }

        return result;
    }

    private static List<Integer> getReverseList(List<Integer> list) {
        List<Integer> result = new ArrayList<Integer>();
        for (int i = list.size() - 1; i >= 0; i--) {
            result.add(list.get(i));
        }
        return result;
    }

    private static int getReplicaIndex(int firstReplicaIndex, int nextReplicaShift, int replicaIndex, int nBrokers) {

        int shift = 1 + (nextReplicaShift + replicaIndex) % (nBrokers - 1);
        int result = (firstReplicaIndex + shift) % nBrokers;
        // System.out.println(firstReplicaIndex+"|"+nextReplicaShift+"|"+replicaIndex+"="+result);
        return result;
    }

    private static void printAssignReplicas(Map<String, List<Integer>> map, List<Integer> brokerListSet) {
        Map<Integer, List<String>> brokerMap = new HashMap<Integer, List<String>>();
        for (int i = 0; i < brokerListSet.size(); i++) {
            List<String> partitions = new ArrayList<String>();
            for (int j = 0; j < map.size(); j++) {
                List<Integer> brokerIds = map.get(j + "");
                if (brokerIds.contains(i)) {
                    partitions.add("" + j);
                }
            }
            brokerMap.put(i, partitions);
        }
    }
}
