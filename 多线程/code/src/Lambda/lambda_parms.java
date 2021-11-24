package Lambda;

public class lambda_parms {
    public static void main(String[] args) {
        ILove  love = null;
        //1.lambda表示简化
        love = (int a)->{
            System.out.println("LOvee"+ a);
        };

        //简化1：参数类型
        love = (a)->{
            System.out.println("LOvee"+ a);
        };

        //简化2：简化括号
        love = a->{
            System.out.println("LOvee"+ a);
        };

        //简化3：简化花括号
        love = a-> System.out.println("LOvee"+ a);

        love.love(205);
    }

}
interface ILove{
    void love(int a);
}