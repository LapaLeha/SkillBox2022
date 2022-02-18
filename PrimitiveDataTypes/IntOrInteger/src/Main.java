public class Main {
    public static void main(String[] args) {
        Container container = new Container();
        container.addCount(5672);
        System.out.println(container.getCount());

        // TODO: ниже напишите код для выполнения задания:
        //  С помощью цикла и преобразования чисел в символы найдите все коды
        //  букв русского алфавита — заглавных и строчных, в том числе буквы Ё.

        /*Вариант 1
        char yoBig = 'Ё';
        char yoSmall = 'ё';
        for (char i = 'А'; i <= 'я'; i++) {
            int c = i;
            System.out.println(i + "-" + c);
        }
        int yoBigCode = yoBig;
        int yoSmallCode = yoSmall;
        System.out.println('Ё'+ "-" +yoBigCode);
        System.out.println('ё'+"-" + yoSmallCode);*/

        //Вариант 2
        for (int i =0;i<65536;i++){
            char c = (char) i;
            if (c>='А'&&c<='я'||c=='Ё'||c=='ё'){
                System.out.println(i+ "-" +c);
            }
        }
    }
}
