/**
 * Ad Soyad: [Abdulkadir Demir]
 * Öğrenci No: [240541007]
 * Proje: [Sinema Bileti Fiyatlandirma]
 * Tarih: [16.11.2025]
 */





import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SinemaBileti {

    static void main() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Yaşınız? ");
        int yas = Integer.parseInt(reader.readLine());

        System.out.println("Saat kaçta?(8 - 23) ");
        int saat = Integer.parseInt(reader.readLine());

        System.out.println("Hangi gün?(1 - 7) ");
        int gun =  Integer.parseInt(reader.readLine());


        switch(gun){
            case 1: System.out.println("Pazartesi"); break;
            case 2: System.out.println("Salı"); break;
            case 3: System.out.println("Çarşamba"); break;
            case 4: System.out.println("Perşembe"); break;
            case 5: System.out.println("Cuma"); break;
            case 6: System.out.println("Cumartesi"); break;
            case 7: System.out.println("Pazar"); break;
            default: System.out.println("Yanlış gün sayısı"); break;
        }

        System.out.println("Mesleğiniz nedir?(1 - 3) ");
        int meslek = Integer.parseInt(reader.readLine());

        switch(meslek){
            case 1: System.out.println("Öğrenci"); break;
            case 2: System.out.println("Öğretmen"); break;
            case 3: System.out.println("Diğer"); break;
            default: System.out.println("Yanlış meslek numarası"); break;
        }

        System.out.println("Film türü seçiniz (1 - 4)");
        int film = Integer.parseInt(reader.readLine());

        switch(film){
            case 1: System.out.println("2D"); break;
            case 2: System.out.println("3D"); break;
            case 3: System.out.println("IMAX"); break;
            case 4: System.out.println("4DX"); break;
            default: System.out.println ("Yanlış film numarası"); break;

        }
        boolean haftasonuMu = isWeekend(gun);
        boolean matineMi = isMatinee(saat);
        int temelFiyat = calculateBasePrice(gun, saat);
        int indirim = calculateDiscount(yas, meslek, gun);
        int ekstraUcret = getFormatExtra(film);
        int toplamFiyat = calculateFinalPrice(temelFiyat, indirim, ekstraUcret);

        System.out.println("\n===BILET FIYATLANDIRMA SISTEMI===");
        System.out.printf("\nGun (1 - 7)       : %d", gun);
        System.out.printf("\nSaat (8 - 23)     : %d", saat);
        System.out.printf("\nYaş               : %d", yas);
        System.out.printf("\nMeslek (1 - 3)    : %d", meslek);
        System.out.printf("\nFilm Türü (1 - 4) : %d", film);
        System.out.println("\n-------------------------");
        System.out.printf("\nHaftasonu kontrol : %b", haftasonuMu);
        System.out.printf("\nMatine            : %b", matineMi);
        System.out.printf("\nTemel fiyat       : %d TL", temelFiyat);
        System.out.printf("\nIndirim           : %d%%", indirim);
        System.out.printf("\nToplam fiyat      : %d TL" , toplamFiyat);

        reader.close();

    }
    static boolean isWeekend(int day){//METOT1 : HAFTASONU KONTROLU
        return (day == 6 || day == 7);
    }
    static boolean isMatinee(int hour) {//METOT2: MATINE KONTROLU
        return hour < 12;
    }
    static int calculateBasePrice(int gun, int saat) {//METOT3: TEMEL FIYAT HESAPLAMA

        int temelFiyat = 0;

        if (saat < 12) {

            temelFiyat = 45;
        } else if (gun == 1 || gun == 2 || gun == 3 || gun == 4 || gun == 5 && (saat >= 12)) {

            temelFiyat = 65;
        } else if ((gun == 6 || gun == 7) && saat < 12) {

            temelFiyat = 55;
        } else if ((gun == 6 || gun == 7) && saat >= 12) {

            temelFiyat = 85;

        }
        return temelFiyat;
    }
    //METOT4: INDIRIM HESAPLAMA
    static int calculateDiscount(int yas, int meslek, int gun){
        //O˘grenci: %20 (Pazartesi-Per¸sembe), %15 (Cuma-Pazar)
        // 65+ ya¸s: %30 (her g¨un)
        // 12 ya¸s altı: %25 (her g¨un)
        // ¨ O˘gretmen: %35 (sadece C¸ar¸samba)

        int discount = 0;

        if(meslek == 1 && gun == 1 || gun == 2 || gun == 3 || gun == 4)
            discount = 20;
        else if(meslek == 2 && gun == 5 || gun == 6 || gun == 7)
            discount = 15;
        else if(yas > 65)
            discount = 30;
        else if(yas < 12)
            discount = 25;
        else if(meslek == 2 && gun == 3)
            discount = 35;

        return discount;
    }

    //METOT5: FORMAT EKSTRA UCRET HESAPLAMA
    static int getFormatExtra(int filmTuru){
        //3D film: +25 TL
        // IMAX: +35 TL
        // — 4DX: +50 TL

        int extra = 0;

        if(filmTuru == 2)
            extra = 25;
        else if(filmTuru == 3)
            extra = 35;
        else if(filmTuru == 4)
            extra = 50;
        return extra;
    }

    //METOT6 TOPLAM FIYAT HESAPLAMA
    static int calculateFinalPrice(int temelFiyat, int indirim, int extra){
        //Temel fiyat: 45 TL (hafta i¸ci matine)
        // ¨ O˘grenci indirimi: %20 → 9 TL
        // ˙ Indirimli fiyat: 36 TL
        // 3D ekstra: +25 TL
        // Toplam: 61 TL,



        int indirimliFiyat = temelFiyat - (temelFiyat * indirim) / 100;
        int toplamFiyat = indirimliFiyat + extra;

        return toplamFiyat;




    }

    //METOT7 BILET BILGISI OLUSTURMA
    static String generateTicketInfo (int gun, int saat, int yas, int meslek, int film,
                                      boolean haftasonuMu, boolean matineMi,
                                      int temelFiyat, int indirim, int toplamFiyat){

        String gunAdi = switch (gun) {
            case 1 -> "Pazartesi";
            case 2 -> "Salı";
            case 3 -> "Çarşamba";
            case 4 -> "Perşembe";
            case 5 -> "Cuma";
            case 6 -> "Cumartesi";
            case 7 -> "Pazar";
            default -> "Geçersiz gün";
        };

        String meslekAdi = switch (meslek) {
            case 1 -> "Öğrenci";
            case 2 -> "Öğretmen";
            case 3 -> "Diğer";
            default -> "Geçersiz meslek";
        };

        String filmTuru = switch (film) {
            case 1 -> "2D";
            case 2 -> "3D";
            case 3 -> "IMAX";
            case 4 -> "4DX";
            default -> "Geçersiz film";
        };

        return  "\n=== BİLET BİLGİSİ ===\n" +
                "Gün: " + gunAdi + "\n" +
                "Saat: " + saat + "\n" +
                "Yaş: " + yas + "\n" +
                "Meslek: " + meslekAdi + "\n" +
                "Film türü: " + filmTuru + "\n" +
                "------------------------\n" +
                "Hafta sonu mu: " + haftasonuMu + "\n" +
                "Matine mi: " + matineMi + "\n" +
                "Temel fiyat: " + temelFiyat + " TL\n" +
                "İndirim: %" + indirim + "\n" +
                "Toplam fiyat: " + toplamFiyat + " TL\n";
    }



}

