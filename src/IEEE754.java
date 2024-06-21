import java.security.PublicKey;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class IEEE754 {

    private double numero;


    public IEEE754(){
        Scanner sc = new Scanner(System.in);
        numero = sc.nextDouble();
    }

    public boolean esNegativo(){
        if(this.numero < 0){
            return true;
        }else{
            return false;
        }
    }


    public String parteDecimal(double numeritoComa){
        String sc  = "";
        boolean result = false;

        while(!result){
            numeritoComa = numeritoComa * 2;
            if(numeritoComa>1){
                sc = sc + "1";
                numeritoComa-=1;
            }else if(numeritoComa<1) {
                sc = sc + "0";
            }else if (numeritoComa == 1){
                sc = sc + "1";
                result=true;
            }
        }

        return sc;

    }

    public String crearMantiza(String numeroEntero, String numeroComa) {
        int tamañoNuevaComa = numeroEntero.length() - 1;
        String despuesDeLaComa = numeroEntero.substring(1, numeroEntero.length());
        String antesDeLaComa = numeroEntero.substring(0, 1);

        String mantiza = "" + despuesDeLaComa + numeroComa;
        int numMantiza = mantiza.length();

        if (numMantiza < 23) {
            for (int i = numMantiza; i < 23; i++) {
                mantiza += "0";
            }
        }

        return mantiza;
    }

    public String antesMantiza(String numeroEntero, String numeroComa){
        String despuesDeLaComa = numeroEntero.substring(1, numeroEntero.length());
        int potencia = despuesDeLaComa.length();
        int potenciaABinario = 0;

        if(potencia>0){
            potenciaABinario = potencia + 127;
        }else{
            potenciaABinario = potencia - 127;
        }
        String binarioPotencia = Integer.toBinaryString(potenciaABinario);

        return binarioPotencia;
    }

    public String crearNumeroCompleto(String mantiza, String potencia){
        boolean valorNumero = esNegativo();
        String numero = "";
        if(valorNumero){
            numero += "1";
        }else{
            numero += "0";
        }
        numero += potencia;
        numero += mantiza;

        return numero;
    }

    public void separarFlotante() {
        String numeroCadena = Double.toString(this.numero);
        String[] palabritas = numeroCadena.split("\\.");
        int parteEntera = (int) this.numero;
        double parteDecimal = this.numero - parteEntera;

        //BigDecimal bdParteDecimal = new BigDecimal(parteDecimal).setScale(2, RoundingMode.HALF_UP);
        //double parteDecimalRedondeada = bdParteDecimal.doubleValue();

        String binarioParteEntera = Integer.toBinaryString(parteEntera);
        String binarioParteDecimal = parteDecimal(parteDecimal);

        String mantiza = crearMantiza(binarioParteEntera, binarioParteDecimal);
        String potencia = antesMantiza(binarioParteEntera, binarioParteDecimal);
        String numero = crearNumeroCompleto(mantiza, potencia);

        System.out.println("Parte entera: " + parteEntera);
        System.out.println("Parte decimal: " + parteDecimal);
        System.out.println("Parte entera en binario: " + binarioParteEntera);
        System.out.println("Mantiza: " + mantiza);
        System.out.println("Binario de la potencia: "+potencia);
        System.out.println("Numero completo: "+numero);
    }


    }


