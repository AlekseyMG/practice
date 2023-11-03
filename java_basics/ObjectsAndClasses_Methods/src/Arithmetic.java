public class Arithmetic {
    public int num1 = 0;
    public int num2 = 0;
    public int sum(int num1, int num2){
        return num1 + num2;
    }
    public int multilier(int num1, int num2){
        return num1 * num2;
    }
    public int maxNum(int num1, int num2){
        if (num1 > num2) {
            return num1;
        }
        return num2;
    }
    public int minNum(int num1, int num2){
        if (num1 < num2) {
            return num1;
        }
        return num2;
    }

}
