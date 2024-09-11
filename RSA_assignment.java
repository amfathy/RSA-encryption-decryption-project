import java.util.Scanner;
public class RSA {


    public static void encryptionANDdecryption(int p, int q, String msg)
    {

        System.out.println("p = "+p+" q = "+q);
        int n=p*q;
        System.out.println("n = p*q = "+n);
        int z=(p-1)*(q-1); //phay(n)
        System.out.println("z = (p-1)*(q-1) = "+z);
        int e=getE(z);
        System.out.println("e = "+e);
        int d=invPow(e,z);//e inv mod phay,e power "pubkey"
        int d2;
        System.out.println("d = "+d);

        char []text = msg.toCharArray();
        int []aski = new int[msg.length()];
        int []encrypted=new int[msg.length()];
        int []decrypted=new int[msg.length()];

        for (int i=0;i<text.length;i++)
        {
            aski[i]=(int)text[i];
        }
        print(msg,text,aski);
        d2=squareANDmultiply(-1,e,z);
        System.out.println(d2);
        for (int i=0;i<text.length;i++)
        {
            encrypted[i]=squareANDmultiply(e,aski[i],n);
            decrypted[i]=squareANDmultiply(d,encrypted[i],n);
            System.out.println("For m = "+text[i]);
            System.out.println("The encryption of m = "+aski[i]+" is c = "+aski[i]+"^"+e+"%"+n+" = "+encrypted[i]);
            System.out.println("The encryption of c = "+encrypted[i]+" is c = "+encrypted[i]+"^"+d+"%"+n+" = "+decrypted[i]);
        }
        String dText="";
        for (int i=0; i<decrypted.length;i++)
        {

            dText+=new Character((char) decrypted[i]).toString();
        }
        finalPrint(msg,dText,encrypted,decrypted,aski);
        System.out.println();
        System.out.println("Decryption in CRT:");
        for (int i=0; i<encrypted.length;i++)
        {
            System.out.println("Char= "+crt(p,q,encrypted[i],d));
        }
        System.out.println("Decrypted Message: " + dText);
    }

    private static int getE(int z)
    {
        int e;
        for (e=2;e<z;e++)
        {
            if (gcd(e,z) == 1)
            {
                break;
            }
        }
        return e;
    }

    private static int gcd(int e, int z)
    {
        if (e == 0)
        {
            return z;
        }
        else
            return gcd(z%e,e);
    }

    private static int squareANDmultiply(int e, int m, int n)
    {
        int y = m;
        String s = Integer.toBinaryString(e);
        for (int i=1;i<s.length();i++)
        {
            y=(y*y)%n;
            if (s.charAt(i) == '1')
            {
                y=(y*m)%n;
            }
        }
        return y;
    }

    private static int invPow (int a, int b) {
        int x = 0;
        for (int i = 1; i<b; i++) {
            if ((a*i) % b == 1)
            {
                x = i;
                break;
            }
        }
        return x;
    }

    private static char crt(int p, int q, int y, int d) {
        System.out.println("The Encryption of Cipher: " + y);
        int n  = p * q;
        int cp = invPow(q,p); //q inv mod p
        int cq = invPow(p,q);
        System.out.println("cp = " + cp + " and cq = " + cq);
        int xp = y % p; // x mod p
        int xq = y % q;
        System.out.println("xp = " + xp + " and xq = "+xq);
        int dp = d % (p-1); //d mod p-1
        int dq = d % (q-1);
        System.out.println("dp = " + dp + " and dq = "+dq);
        int yp = (squareANDmultiply(dp, xp,p)) ; //yp= xp exp dp mod p
        int yq = (squareANDmultiply(dq, xq,q));
        System.out.println("yp = " + yp + " and yq = " + yq);
        int x = ((q*cp)*yp + (p*cq)*yq) % n; // inversr trans third step
        System.out.println("ASCII = " + x);
        char ch = (char) x;
        return ch;
    }

    private static int mPower(int x, int e, int m) {
        int res = 1;
        while (e > 0) {
            if ((e % 2) == 1) {
                res = (res * x) % m;
                e--;
            } else {
                x = (x * x) % m;
                e = e / 2;
            }
        }
        return res;
    }

    private static boolean isPrime(int n, int k) {
        if (n % 2 == 0 && n != 2)  {
            return false;
        }
        if (n <= 3)  {
            return true;
        }
        while (k > 0) {
            int a = (int) Math.random() * (n - 3) + 2;
            if (mPower(a, n - 1, n) != 1) {
                return false;
            }
            k--;
        }
        return true;
    }

    public static void print(String msg,char[] m, int[] a)
    {
        System.out.print("The message = "+msg);
        for (int i=0;i<m.length;i++)
        {
            System.out.print(" "+m[i]+" -> "+a[i]);
        }
        System.out.println();
    }

    private static void finalPrint(String msg, String dText, int []encrpted,int []decrypted , int [] aski)
    {
        System.out.println();
        System.out.println("Message to encrypt : "+msg);
        System.out.print("Message converted to ASCII code: ");
        for (int i=0; i<aski.length;i++)
        {
            System.out.print(aski[i]+",");
        }
        System.out.println();
        System.out.print("Encrypted Message: ");
        for (int i=0; i<encrpted.length;i++)
        {
            System.out.print(encrpted[i]+",");
        }
        System.out.println();
        System.out.print("Message decrypted to ASCII code: ");
        for (int i=0; i<encrpted.length;i++)
        {
            System.out.print(decrypted[i]+",");
        }
        System.out.println();
        System.out.println("Decrypted Message: "+dText);
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int p;
        int q;
        String msg;
        System.out.println("Enter value of first prime number (p) ");
        p = input.nextInt();
        System.out.println("Enter value of second prime number (q) ");
        q = input.nextInt();
        System.out.println("Enter The message ");
        msg = input.next();
        while (!isPrime(p,3) || !isPrime(q,3)){
            System.out.println("Please enter Primes numbers");
            System.out.println("Enter value of first prime number (p) ");
            p = input.nextInt();
            System.out.println("Enter value of second prime number (q) ");
            q = input.nextInt();
        }

        encryptionANDdecryption(p,q,msg);
    }
}
