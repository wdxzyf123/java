package leetcode;
//优先级队列，执行插入删除时保持顺序
public class Priorrity_Queue {

    public static void main(String[] args) {

    }


}
class MaxPQ{
    int[] pq;
    int N = 0;
    public int max() {
        return pq[0];
    }
    public void insert(int e) {

    }
    public void delMax() {

    }
    public void swim(int k) {

    }
    public void  sink(int k) {

    }
    public void swap(int i, int j) {
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }
    public boolean less(int i, int j) {
        if (pq[i] < pq[j])
            return true;
        else
            return false;
    }
    public static int parent(int root){
        return (root-1)/2;
    }
    public static int left(int root) {
        return 2*root+1;
    }
    public static int right(int root) {
        return 2*root+2;
    }

}