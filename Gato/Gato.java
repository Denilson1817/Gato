import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
public class Gato extends JFrame implements ActionListener{
	JButton iniciar,reiniciar,finalizar,guardar_Partida,partida;
	JButton tablero[][];
	String jugador1,jugador2;
	int turno=-1, cj1, cj2;
	JLabel mensaje, j1, j2, m1;
	Color colorB;
	public Gato(){
		this.setLayout(null);
        m1 = new JLabel("partidas ganadas");
        m1.setBounds(20,0,200,30);
        this.add(m1);

        j1 = new JLabel("");
        j1.setBounds(20,12,200,30);
        this.add(j1);

        j2 = new JLabel("");
        j2.setBounds(20,24,200,30);
        this.add(j2);

		mensaje = new JLabel("Bienvenido al juego");
		mensaje.setBounds(150,40,200,30);
		this.add(mensaje);

		iniciar = new JButton("iniciar juego");
		iniciar.setBounds(50,350,120,30);
		iniciar.addActionListener(this);
		this.add(iniciar);
		
        reiniciar = new JButton("reiniciar");
		reiniciar.setBounds(200,350,120,30);
		reiniciar.addActionListener(this);
		this.add(reiniciar);
		
        finalizar = new JButton("finalizar");
		finalizar.setBounds(350,350,120,30);
		finalizar.addActionListener(this);
		this.add(finalizar);
		
        guardar_Partida = new JButton("Guardar Partida");
		guardar_Partida.setBounds(200,400,120,30);
		guardar_Partida.addActionListener(this);
		this.add(guardar_Partida);

        partida = new JButton("Cargar Partida");
		partida.setBounds(350,400,120,30);
		partida.addActionListener(this);
		this.add(partida);

		tablero = new JButton[3][3];
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				tablero[i][j] = new JButton();
				tablero[i][j].setBounds((i+1)*80+50,(j+1)*80,80,80);
				this.add(tablero[i][j]);
				tablero[i][j].addActionListener(this);
			}
		}
		colorB = tablero[0][0].getBackground();
	}

	public static void main(String[] args){
		Gato ventana = new Gato();
		ventana.setDefaultCloseOperation(3);
		ventana.setSize(500,550);
		ventana.setLocationRelativeTo(null);
		ventana.setResizable(false);
		ventana.setTitle("Gato");
		ventana.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==iniciar){
			turno = 0;
			int validacion=0;
			jugador1 = JOptionPane.showInputDialog(this,"Nombre del jugador 1");
			if(jugador1.equals("")){
				jugador1 ="jugador1";
			}
            j1.setText(jugador1);
			jugador2 = JOptionPane.showInputDialog(this,"Nombre del jugador 2");
			if(jugador2.equals("")){
				jugador2 ="jugador2";
			}
            j2.setText(jugador2);
			mensaje.setText("Turno del jugador " + jugador1);
			limpiar();
		}else{
			JButton boton = (JButton) e.getSource();
			if(turno == 0){
				if(boton.getText().equals("")){
					boton.setBackground(Color.black);
					boton.setText("X");
					boton.setEnabled(false);
					turno = 1;
					mensaje.setText("Turno del jugador " + jugador2);
                    verificar();
				}
			}else{
				if(turno==1){
					if(boton.getText().equals("")){
					boton.setBackground(Color.white);
					boton.setText("O");
					boton.setEnabled(false);
					turno = 0;
					mensaje.setText("Turno del jugador " + jugador1);
                    verificar();
				}
				}
            }
			
		}
			if(e.getSource()==reiniciar){
			turno = 0;
			JOptionPane.showMessageDialog(this,"juego reiniciado");
            cj1=0;
            cj2=0;
            j1.setText(jugador1 + ": " + cj1);
            j2.setText(jugador2 + ": " + cj2);
			limpiar();
		}
			if(e.getSource()==finalizar){
			int a= JOptionPane.showConfirmDialog(this,"Desea salir del juego");
			if (a==0){
                this.dispose();
            }
		}
		if(e.getSource()==guardar_Partida){
			guardar();
		}
    
         if(e.getSource()==partida){
             cargar();
		}
    }
	public void verificar(){
		int ganador=0; 
		for(int i=0;i<3;i++){
			if(tablero[0][i].getText().equals("X")&&tablero[1][i].getText().equals("X")
				&& tablero[2][i].getText().equals("X")){
					ganador = 1;
			}
			if(tablero[i][0].getText().equals("X")&&tablero[i][1].getText().equals("X")
				&& tablero[i][2].getText().equals("X")){
					ganador = 1;
			}
		}
		if(tablero[0][0].getText().equals("X")&&tablero[1][1].getText().equals("X")
				&& tablero[2][2].getText().equals("X")){
					ganador = 1;
			}
		if(tablero[0][2].getText().equals("X")&&tablero[1][1].getText().equals("X")
				&& tablero[2][0].getText().equals("X")){
					ganador = 1;
			}
		if(ganador == 1){
			mensaje.setText("partida terminada");
            cj1=cj1 + 1;
            j1.setText(jugador1 + ": " + cj1);
            int b = JOptionPane.showConfirmDialog(this,"Ganador: " + jugador1 + " Desea continuar la partida?");
            if (b == 0 ){
                limpiar();
            }else{
			    bloquear();
            }
		}
			//Jugador2 Verticales y Horizontales
					for(int i=0;i<3;i++){
			if(tablero[0][i].getText().equals("O")&&tablero[1][i].getText().equals("O")
				&& tablero[2][i].getText().equals("O")){
					ganador = 2;
			}
			if(tablero[i][0].getText().equals("O")&&tablero[i][1].getText().equals("O")
				&& tablero[i][2].getText().equals("O")){
					ganador = 2;
			}
		}
		if(tablero[0][0].getText().equals("O")&&tablero[1][1].getText().equals("O")
				&& tablero[2][2].getText().equals("O")){
					ganador = 2;
			}
		if(tablero[0][2].getText().equals("O")&&tablero[1][1].getText().equals("O")
				&& tablero[2][0].getText().equals("O")){
					ganador = 2;
			}
		if(ganador == 2){
            mensaje.setText("partida terminada");
            cj2=cj2 + 1;
            j2.setText(jugador2 + ": " + cj2);
			int b = JOptionPane.showConfirmDialog(this,"Ganador: " +jugador2 + " desea continuar la partida?");
			if (b == 0 ){
                limpiar();
            }else{
			    bloquear();
            }
		}
	}

	public void bloquear(){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				tablero[i][j].setEnabled(false);
			}
		}
	}

	public void limpiar(){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				tablero[i][j].setEnabled(true);
				tablero[i][j].setText("");
				tablero[i][j].setBackground(colorB);
			}
		}
	}

    public void guardar(){
        String partida = jugador1 + "," + jugador2 + "," + cj1 + "," +cj2 + "," + turno + ",";
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
                String orden = tablero[j][i].getText();
                if (orden.equals("")){
                    partida = partida + " ,";
                }else{
                    partida = partida + orden + ",";
                }
			}
		}
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("./partida.txt");
            pw = new PrintWriter(fichero);

                pw.println(partida);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
	}

    public void cargar(){
        File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;

      try {
         archivo = new File ("./partida.txt");
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);

         String linea;
         linea=br.readLine();
         String partida [] = linea.split(",");
         jugador1 = partida[0];
         jugador2 = partida[1];
         cj1 = Integer.parseInt(partida[2]);
         cj2 = Integer.parseInt(partida[3]);
         j1.setText(jugador1 + ": " + cj1);
         j2.setText(jugador2 + ": " + cj2);
         turno = Integer.parseInt(partida [4]);
         int y=5;
            for(int i=0;i<3;i++){
                for (int j =0;j<3;j++){
                    if(partida[y].equals("X")){
                        tablero[j][i].setBackground(Color.black);
					    tablero[j][i].setText("X");
					    tablero[j][i].setEnabled(false);
                    }else if(partida[y].equals("O")){
                        tablero[j][i].setBackground(Color.white);
					    tablero[j][i].setText("O");
					    tablero[j][i].setEnabled(false);
                    }
                    y++;
                }
            }
      }catch(Exception e){
         e.printStackTrace();
      }finally{
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
    }
}