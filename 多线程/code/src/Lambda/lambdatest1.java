package Lambda;

public class lambdatest1 {
    //静态内部类
    static class Like2 implements Ilike{

        @Override
        public void lambda() {
            System.out.println("i like lambda_静态内部类");
        }
    }

    public static void main(String[] args) {
        //平时用的方式
        //Like like = new Like();
        Ilike like = new Like();
        like.lambda();

        //静态内部类
        Like2 like2 = new Like2();
        like2.lambda();

        //局部内部类
        class Like3 implements Ilike {

            @Override
            public void lambda() {
                System.out.println("i like lambda_局部内部类");
            }
        }

        Like3 like3 = new Like3();
        like3.lambda();

        //匿名内部类
        Ilike like4 = new Ilike() {
            @Override
            public void lambda() {
                System.out.println("i like lambda_匿名内部类");
            }
        };
        like4.lambda();

        //lambda简化
        Ilike like5 = () ->{
            System.out.println("i like lambda简化");
        };
        like5.lambda();

        //简化花括号
        Ilike like6 = () -> System.out.println("i like lambda简化括号");
        like6.lambda();

    }

}

// 1.定义一个函数式接口
interface Ilike {
    void lambda();
}
// 2.实现类
class Like implements Ilike {

    @Override
    public void lambda() {
        System.out.println("i like lambda");
    }
}