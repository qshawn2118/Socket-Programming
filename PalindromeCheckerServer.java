import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class PalindromeCheckerServer {

    ServerSocket ss;
    Socket socket;
    BufferedReader sock_in;
    BufferedReader kdb_in;
    PrintWriter sock_out;
    String str;

    public static void main(String arg[]) {
        new PalindromeCheckerServer();
    }

    public PalindromeCheckerServer() {

        boolean foo = true;
        try{
            ss = new ServerSocket(8765);
            System.out.println("PalindromeCheckerServer is listening 8765");
            socket = ss.accept();
            System.out.println("Connection established...");
            kdb_in = new BufferedReader(new InputStreamReader(System.in));
            sock_in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            sock_out = new PrintWriter(socket.getOutputStream());

            //Begin the process as long as foo is true
            while(foo) {
                System.out.println( "Message from client" );
                str = sock_in.readLine();
                int k = str.length();
                System.out.println( str );

                //If bye is entered, the program will stop
                if (str.equals( "bye" ))
                    foo = false;

                //Beginning of Palindrome Checker code
                //Reads the string and back to make sure the chars match
                int left = 0;
                int right = k - 1;
                boolean palindrome = true;
                while (left <= right) {
                    //if the chars do not match, process will cease
                    if (str.charAt( left ) != (str.charAt( right ))) {
                        palindrome = false;
                        break;
                    } else {
                        left++;
                        right--;
                    }
                }

                //Check the value of palindrome
                if (palindrome) {
                    str = "....Palindrome....";
                } else {
                    str = "....Not Palindrome....";
                }
                sock_out.println( str );
                sock_out.flush();
            }
        }catch (Exception e) { }
    }
}

