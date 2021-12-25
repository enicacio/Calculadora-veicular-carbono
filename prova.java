public class prova {
    public static void main(String[] args) {
        int n = 2;
        int x = 3;
        int a = 2;
        int resp = x;
        int ep, b;


        while (a <= 4){
            ep = x;
            b = 1;

            while(b<a){
                ep = ep * x;
                b = b+1;
            }
            resp = resp + ep;
            a = a + 2;
        }

    System.out.println(resp);
    }
}
