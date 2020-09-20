package icop.test.queue.compare;

/**
 * @author: liukj
 * @date: 2020/8/7
 * @description：
 */
public class Cat implements Comparable<Cat> {
    private int weight;
    private int height;

    public Cat(int weight, int height) {
        this.weight = weight;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "weight=" + weight +
                ", height=" + height +
                '}';
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int compareTo(Cat o) {
        if(this.weight < o.weight){
            return 1;
        }else if(this.weight == o.weight){
            return 0;
        }else {
            return -1;
        }
    }
}
