import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Cronometro extends JFrame implements ActionListener {

    private Timer timer;
    private JLabel tiempoLabel;
    private int horas, minutos, segundos;
    private int alarmaMinutos, alarmaSegundos;
    private boolean alarmaActivada;

    public Cronometro() {
        super("Cronómetro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(250, 100);
        setLayout(new FlowLayout());

        tiempoLabel = new JLabel("00:00:00");
        tiempoLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        add(tiempoLabel);

        JButton startButton = new JButton("Iniciar");
        startButton.addActionListener(this);
        add(startButton);

        JButton stopButton = new JButton("Detener");
        stopButton.addActionListener(this);
        add(stopButton);

        alarmaActivada = false;

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Iniciar")) {
            iniciarCronometro();
        } else if (command.equals("Detener")) {
            detenerCronometro();
        }
    }

    private void iniciarCronometro() {
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                segundos++;

                if (segundos == 60) {
                    segundos = 0;
                    minutos++;
                }

                if (minutos == 60) {
                    minutos = 0;
                    horas++;
                }

                actualizarTiempoLabel();

                if (alarmaActivada && minutos == alarmaMinutos && segundos == alarmaSegundos) {
                    activarAlarma();
                }
            }
        });
        timer.start();
    }

    private void detenerCronometro() {
        timer.stop();
    }

    private void actualizarTiempoLabel() {
        String tiempo = String.format("%02d:%02d:%02d", horas, minutos, segundos);
        tiempoLabel.setText(tiempo);
    }

    private void activarAlarma() {
        Timer alarmaTimer = new Timer(10000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "¡Alarma!");
            }
        });
        alarmaTimer.start();
    }

    public void setAlarma(int minutos, int segundos) {
        alarmaMinutos = minutos;
        alarmaSegundos = segundos;
        alarmaActivada = true;
    }

    public static void main(String[] args) {
        Cronometro cronometro = new Cronometro();
        cronometro.setAlarma(2, 0);
    }

}

