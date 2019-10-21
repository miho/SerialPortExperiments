/*
 * Serial communication with Arduino.
 * 
 * Arduino Code:
 * 
 * <pre>
 * long counter = 0;
 *
 * void setup() {
 *   // start serial port at 9600 bps:
 *   Serial.begin(9600);
 *     while (!Serial) {
 *       // wait for serial port to connect.
 *     }
 *   }
 *
 *   void loop() {
 *     Serial.print("Hello from Arduino: ");
 *     Serial.print(counter);
 *     Serial.print("\n");
 *     delay(1000);
 *     counter++;
 *   }
 * </pre>
 */
package comport;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

import com.fazecast.jSerialComm.SerialPort;

public class App {

    public static void main(String[] args) {

        // select first Arduino (is expected to be connected prior to running this app)
        SerialPort port = Arrays.asList(SerialPort.getCommPorts()).stream()
                .filter(p -> p.getDescriptivePortName().contains("Arduino")).findFirst()
                .orElseThrow(() -> new RuntimeException("Cannot find Arduino COM Port"));

        System.out.println("> reading from port: " + port.getDescriptivePortName());        

        // setup port        
        port.setBaudRate(9600);
        port.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 3000, 3000);

        // open port
        if(!port.openPort()) {
            throw new RuntimeException("Cannot open port: " + port.getDescriptivePortName());
        }

        // read data from COM port
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(port.getInputStream()));) {
            while(true) {
               System.out.println("> reading: " + reader.readLine());
            }
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
        } finally {
            if(!port.closePort()) {
                throw new RuntimeException("Cannot close port: " + port.getDescriptivePortName());
            }
        }
    }
}
