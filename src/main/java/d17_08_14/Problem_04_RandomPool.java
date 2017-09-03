package d17_08_14;

import java.util.HashMap;

/**
 * RandomPool结构
 * 设计一种结构，在该结构中有如下的三种功能：
 * 1.insert(key): 将某个key加入到该结构中，做到不重复加入
 * 2.delete(key): 将原本在结构中的某个key移除
 * 3.getRandom(): 等概率随机返回结构中的任何一个key
 * 要求：每种方法的时间复杂度都是O(1)
 */
public class Problem_04_RandomPool {

    public static class Pool<K> {
        private HashMap<K, Integer> keyIndexMap;   //记录key-index
        private HashMap<Integer, K> indexKeyMap;   //记录index-key
        private int size;

        public Pool() {
            this.keyIndexMap = new HashMap<K, Integer>();
            this.indexKeyMap = new HashMap<Integer, K>();
            this.size = 0;
        }

        public void insert(K key) {
            if (!this.keyIndexMap.containsKey(key)) {
                this.keyIndexMap.put(key, this.size);
                this.indexKeyMap.put(this.size++, key);
            }
        }

        //important  要把最新加入的lastKey放到要删除的deleteKey的位置上
        public void delete(K key) {
            if (this.keyIndexMap.containsKey(key)) {
                int deleteIndex = this.keyIndexMap.get(key);
                int lastIndex = --this.size;
                K lastKey = this.indexKeyMap.get(lastIndex);
                this.keyIndexMap.put(lastKey, deleteIndex);
                this.indexKeyMap.put(deleteIndex, lastKey);
                this.keyIndexMap.remove(key);
                this.indexKeyMap.remove(lastIndex);
            }
        }

        //实现随机产生0~size-1的随机函数即可
        public K getRandom() {
            if (this.size == 0) {
                return null;
            }
            int randomIndex = (int) (Math.random() * this.size);
            return this.indexKeyMap.get(randomIndex);
        }

    }

    public static void main(String[] args) {
        Pool<String> pool = new Pool<String>();
        pool.insert("zuo");
        pool.insert("cheng");
        pool.insert("yun");
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());

    }

}
