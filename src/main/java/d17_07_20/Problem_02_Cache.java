package d17_07_20;

/**
 * 工程师常使用服务器集群来设计和实现数据缓存，以下是常见的策略：
 * 1．无论是添加、查询还是删除数据，都先将数据的id通过哈希函数转换成一个哈希值，记为key。
 * 2．如果目前机器有N台，则计算key%N的值，这个值就是该数据所属的机器编号，
 * 无论是添加、删除还是查询操作，都只在这台机器上进行。
 * 请分析这种缓存策略可能带来的问题，并提出改进的方案。
 */
public class Problem_02_Cache {


    /*
        经典的缓存结构存在的问题（废弃了）：
        如果想新加或者减少一台机器，就要原先机器上的数据重新模上现在的机器数，做大量的数据迁移


        一致性哈希
        虽然说s(输出域)这个域有限，但是还是很大的，如2的128次方
        想象一个环状的哈希空间，哈希有一个域环，每台机器根据mac地址算出一个哈希码，标在哈希环上，
        每个算出来的hashcode，在环上顺时针找，碰到哪个机器，这个数据就归哪个机器。
        （把所有的哈希码存成一个有序数组，技术上有二分来找）
        在工程上还要用到虚拟节点技术，一个机器，用1000个虚拟节点都代表这个机器，将这个哈希空间用机器数
        来平均划分
    */
}
