package mx.edu.ittepic.compilador;

/**
 * Created by franciscoemanuelcardenasramos on 18/07/18.
 */

public class GeneradorDeCodigo {

    public static String cod = "#include <Servo.h> \n"
            + "Servo servo1; \n"
            + "Servo servo2; \n"
            + "Servo servo3; \n"
            + "Servo servo4; \n"
            +"void setup (){\n"
            + "{\n"
            + "servo1.attach(12,650,2400); \n"
            + "servo2.attach(11,650,2400); \n"
            + "servo3.attach(10,650,2400); \n"
            +   "servo4.attach(9,650,2400); \n"
            + "}\n"

            //Valencia  has de cuenta asi los metodos para la garra y el carro
            + "void abrir(int angulo){  \n "
            + "servo1.write(angulo); \n"
            + "delay(2000); \n"
            + "servo1.write(0); \n"
            + " } \n"

            ;






    public static String convrt(String []codSM, String []codM){
        String []lineasM = codM;
        String []lineasSM = codSM;
        String []aux1;
        String []aux2;
        for(int i = 0; i < lineasM.length; i++){
            aux1 =  lineasM[i].split(" ");
            aux2 =  lineasSM[i].replace("VERDADERO", "true").replace("FALSO", "false").split(" ");
            switch(aux1[0]){
                case "INICIO":{
                    cod += "void main"+"(){\n";
                    break;
                }
                case "CICLO":{
                    cod += "for(int i0000=0;i0000<"+aux2[2]+";i0000++ ){\n";
                    break;
                }
                case "ENTERO":{
                    cod += lineasM[i].replace("ENTERO", "int")+"\n";
                    break;
                }
                case "BOOLEANO":{
                    cod += lineasM[i].replace("BOOLEANO", "bool")+"\n";
                    break;
                }
                case "CADENA":{
                    cod += lineasM[i].replace("CADENA", "string")+"\n";
                    break;
                }
                case "SI":{
                    cod += lineasM[i].replace("SI", "if")+"\n";
                    break;
                }

                case "SINO":{
                    cod += lineasM[i].replace("SINO", "else")+"\n";
                    break;
                }

                //Escribir asi men V:
                case "GARRA.ABRIR":{
                    cod += "abrir("+aux2[2]+");\n";
                    break;
                }




                default:{
                    cod+=lineasSM[i]+"\n";
                    break;
                }
            }
        }
        System.out.print(cod);
        return cod+="}";
    }

    public static String opt(){
        String codOp="";
        String []sen = cod.split("\n");
        int index=0;
        boolean bandera = false;
        for(int i =0; i < sen.length; i++){
            if(sen[i].equals("public void setup(){")){
                index = buscarLlave(i);
                bandera = true;
            }
            if(i<=index && bandera){
                continue;
            }
            codOp+=sen[i]+"\n";
        }
        return codOp+"}";
    }

    public static int buscarLlave(int nl){
        int contador = 1;
        String []sen2 = cod.split("\n");
        sen2[nl] = sen2[nl].replaceFirst("\\{ ", "");
        for(int i = nl; i < sen2.length; i++){
            for(int j = 0; j < sen2[i].length(); j++){
                if(sen2[i].charAt(j) == '{'){
                    contador++;
                }
                if(sen2[i].charAt(j) == '}'){
                    contador--;
                }
                if(contador == 0){
                    return i;
                }
            }
        }
        return 0;
    }

}
