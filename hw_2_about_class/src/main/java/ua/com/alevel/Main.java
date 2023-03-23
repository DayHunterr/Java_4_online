package ua.com.alevel;

import java.util.Arrays;

public class Main {
    private static int count = 0;
    public static void main(String[] args) {

        Human human1 = new Human("Van", 30, "Main Message 1", false);
        Human human2 = new Human("Bon", 27, "Message 2", FoolParametr.IsFool((int) (Math.random() * 6)));
        Human human3 = new Human("Non", 24, "Message 3", FoolParametr.IsFool((int) (Math.random() * 6)));
        Human human4 = new Human("Fon", 22, "Message 4", FoolParametr.IsFool((int) (Math.random() * 6)));
        Human human5 = new Human("Gon", 20, "Message 5", FoolParametr.IsFool((int) (Math.random() * 6)));
        Human human6 = new Human("Pon", 18, "Message 6", FoolParametr.IsFool((int) (Math.random() * 6)));


        while (!human1.getMessage().equals(human2.getMessage()) || !human1.getMessage().equals(human3.getMessage()) ||
                !human1.getMessage().equals(human4.getMessage()) ||
                !human1.getMessage().equals(human5.getMessage()) ||
                !human1.getMessage().equals(human6.getMessage())) {
            if(!human2.isFool()){
                human2.setMessage(human1.getMessage());
                if(!human3.isFool()){
                    human3.setMessage(human2.getMessage());
                    if(!human4.isFool()){
                        human4.setMessage(human3.getMessage());
                        if (!human5.isFool()){
                            human5.setMessage(human4.getMessage());
                            human6.setMessage(human5.getMessage());
                        } else {
                            human5.setFool(FoolParametr.IsFool((int)(Math.random() * 6)));
                        }
                    } else human4.setFool(FoolParametr.IsFool((int)(Math.random() * 6))) ;
                } else human3.setFool(FoolParametr.IsFool((int)(Math.random() * 6)));


            }else human2.setFool(FoolParametr.IsFool((int)(Math.random() * 6)));

            System.out.println("Итерация: " + ++count);
            System.out.println(human1);
            System.out.println(human2);
            System.out.println(human3);
            System.out.println(human4);
            System.out.println(human5);
            System.out.println(human6);
            System.out.println("-----------------------------------------------------------------");


        }


    }


}











































////        Human human123 = new Human();
////        System.out.println(human123.info());
////        human123.name = "Alex";
////        human123.age = 20;
////        System.out.println(human123.info());
//        int a = 10;
//        long l = (long) a;
//        A aa = new A();
//        B bb = new B();
////        C cc = new C();
//
//        aa = new A();
//        aa = new B();
//        aa = new C();
//
//        bb = new B();
//        bb = new C();
//
//
//        Integer i = 10;
////        i = "test";
//
//        var cc = new C();
////        val cc = new C();
//        var aaa = new Ajkggsfdgasdcgfadgfasdfgasdgfasdgfasdfgasdfgsadfgasdfgasdafgdsafgdfgd();
//
////        bb = aa;
//}
//}
