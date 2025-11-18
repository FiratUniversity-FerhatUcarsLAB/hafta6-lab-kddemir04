/**
 * Ad Soyad: [Abdulkadir Demir]
 * Öğrenci No: [240541007]
 * Proje: [Akiili Restoran Siparis Sistemi]
 * Tarih: [18.11.2025]
 */
import java.util.Scanner;

public class RestoranSiparis {


    static double getMainDishPrice(int secim){
        switch(secim){//Ana yemek fiyatı
            case 1: return 85;//Izgara-tavuk
            case 2: return 120;//Adana kebap
            case 3: return 110;// Levrek
            case 4: return 65;//Mantı
            default: return 0;
        }

    }
    static double getAppetizerPrice(int secim){//Başlangıç fiyatı
        switch(secim){
            case 1: return 25;//Çorba
            case 2: return 45;//Humus
            case 3: return 55;//Sigara böreği
            default: return 0;
        }
    }
    static double getDrinkPrice(int secim){//İçecek fiyatı
        switch(secim){
            case 1: return 15;//Kola
            case 2: return 12;//Ayran
            case 3: return 35;//Taze meyve suyu
            case 4: return 25;//Limonata
            default: return 0;
        }
    }
    static double getDessertPrice(int secim){//Tatlı fiyatı
        switch(secim){
            case 1: return 65;//Künefe
            case 2: return 55;//Baklava
            case 3: return 35; //Sütlaç
            default: return 0;
        }
    }
    //Combo mu?
    static boolean isComboOrder(int anaVar, int icecekVar, int tatliVar){
        return (anaVar > 0 & icecekVar > 0 & tatliVar > 0);

    }
    //Happy Hour mu?
    static boolean isHappyHour(int saat){
        return(saat >= 14 && saat <= 17);

    }

    static double calculateDiscount(double tutar, boolean combo, boolean ogrenci, int saat){//indirim
        int indirim = 0;

        if(combo){//Combo indirimi
            indirim += tutar * 0.15;
        }

        if(tutar > 200){
            indirim += (tutar - indirim) * 0.10;
        }

        if(isHappyHour(saat)){
            indirim += (tutar - indirim) * 0.20;
        }

        if(ogrenci) {
            indirim += (tutar - indirim) * 0.10;
        }

        return indirim;

    }
    static double calculateServiceTip(double tutar){
        return tutar * 0.10;
    }

    static void main() {
        Scanner tara = new Scanner(System.in);
        System.out.println("Ana yemek: (1 - 4) ");
        int ana =  tara.nextInt();

        System.out.println("Başlangıç yemeği: (0 - 3) ");
        int bas =  tara.nextInt();

        System.out.println("Içecek: (0 - 4) ");
        int icecek =   tara.nextInt();

        System.out.println("Tatlı: (0 - 3)" );
        int tatli =   tara.nextInt();

        System.out.println("Saat: (8 - 23) ");
        int saat =  tara.nextInt();

        tara.nextLine();

        System.out.println("Öğrenci misiniz? (E / H) ");
        char o = tara.nextLine().toUpperCase().charAt(0);
        boolean ogrenci = (o == 'E');

        //Ara toplam
        double main = getMainDishPrice(ana);
        double appetizer = getAppetizerPrice(bas);
        double drink = getDrinkPrice(icecek);
        double dessert = getDessertPrice(tatli);

        double toplam = main + appetizer + drink + dessert;

        //Durumlar
        boolean combo = isComboOrder(ana, icecek, tatli);
        boolean happy = isHappyHour(saat);

        //indirimler
        double indirim = calculateDiscount(toplam, combo, ogrenci, saat);

        // Happy Hour indirimi sadece içecek fiyatından düşüyor
        double happyIndirim = 0;
        if (happy && icecek > 0) {
            happyIndirim = drink * 0.20;
        }

        // Toplam indirim
        double toplamIndirim = indirim + happyIndirim;
        double netTutar = toplam - toplamIndirim;

        double bahsis = calculateServiceTip(netTutar);


        // --- ÇIKTI ---
        System.out.println("\n----- HESAP -----");
        System.out.println("Ara Toplam: " + toplam + " TL");

        if (combo) System.out.println("Combo indirimi uygulandı (%15)");
        if (toplam >= 200) System.out.println("200 TL üstü indirim uygulandı (%10)");
        if (ogrenci) System.out.println("Öğrenci indirimi uygulandı (%10)");
        if (happy && icecek > 0) System.out.println("Happy Hour indirimi: -" + happyIndirim + " TL");

        System.out.println("Toplam İndirim : -" + toplamIndirim + " TL");
        System.out.printf("\nÖdenecek Tutar: %.2f TL", netTutar);
        System.out.printf("\nBahşiş Önerisi: %.2f TL", bahsis);

        tara.close();
    }






    }





