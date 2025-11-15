/**
 * Ad Soyad: [Abdulkadir Demir]
 * Öğrenci No: [240541007]
 * Proje: [Ogrenci Not Degerlendirme Sistemi]
 * Tarih: [15.11.2025]
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NotSistemi {

    static void main()throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Vize: (0 - 100)");
        double vize = Double.valueOf(reader.readLine());

        System.out.println("Final: (0 - 100)");
        double finalNot = Double.valueOf(reader.readLine());

        System.out.println("Ödev: (0 - 100)");
        double odev =  Double.valueOf(reader.readLine());

        double ort = calculateAverage(vize, finalNot, odev);
        boolean durum = isPassingGrade(ort);
        String harf = getLetterGrade(ort);
        boolean onurListe = isHonorList(ort, vize, finalNot, odev);
        boolean butunleme = hasRetakeRight(ort);


        System.out.println("===ÖĞRENCİ NOT RAPORU===");
        System.out.printf("\nVize Notu   :   %.1f", vize);
        System.out.printf("\nFinal Notu  :   %.1f", finalNot);
        System.out.printf("\nÖdev Notu   :   %.1f", odev);
        System.out.println("\n-------------------------");
        System.out.printf("\nOrtalama    :   %.1f", ort);
        System.out.printf("\nHarf notu   :    %s", harf);
        System.out.printf("\nDurum       :   %s", durum ? "GEÇTİ" : "KALDI");
        System.out.printf("\nOnur listesi:   %s", onurListe ? "EVET" : "HAYIR");
        System.out.printf("\nBütünleme   :   %s", butunleme ? "VAR" : "YOK");

        reader.close();

    }
   static double calculateAverage(double vize, double finalNot, double odev){

        return vize * 0.3 + finalNot * 0.4 + odev * 0.3;

    }
   static boolean isPassingGrade(double ortalama){
        return ortalama >= 50;
   }
    static String getLetterGrade(double average){

        if(average >= 90 && average <= 100)
            return "A";
        else if(average >= 80 && average <= 89)
            return "B";
        else if(average >= 70 && average <= 79)
            return "C";
        else if(average >= 60 && average <= 69)
            return "D";
        else
            return "F";
    }
    static boolean isHonorList(double o, double v, double finalNot, double odev){
        return o >= 85 && v >= 70 && finalNot >= 70 && odev >= 70;
    }
    static boolean hasRetakeRight(double a){
        return a >= 40 && a < 50;
    }
}

