package Snakez;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class tablero extends JPanel implements ActionListener {

    private final int anchoCampo = 500;
    private final int altoCampo = 500;
    private final int cuerpoTamanho = 10;
    private final int maximoCuerpos = 900;
    private final int manzanaUbPos = 29;
    private final int retrasoEnMov = 150;
    
    //jugador uno
    private final int x[] = new int[maximoCuerpos];
    private final int y[] = new int[maximoCuerpos];
    private boolean dirIzq = false;
    private boolean dirDer = true;
    private boolean dirArr = false;
    private boolean dirAba = false;
    private boolean activo1 = true;
    private int cuerpos;
    private int manzanasCom;
    private Image cuerpo;
    private Image cabeza;
    private Image cola;
    private String nombreCola;
    
    //jugador dos
    private final int x2[] = new int[maximoCuerpos];
    private final int y2[] = new int[maximoCuerpos];
    private boolean dirIzq2 = false;
    private boolean dirDer2 = true;
    private boolean dirArr2 = false;
    private boolean dirAba2 = false;
    private boolean activo2 = false;
    private int cuerpos2;
    private int manzanasCom2;
    private Image cuerpo2;
    private Image cabeza2;
    private Image cola2;
    private String nombreCola2;
    
  //jugador tres
    private final int x3[] = new int[maximoCuerpos];
    private final int y3[] = new int[maximoCuerpos];
    private boolean dirIzq3 = false;
    private boolean dirDer3 = true;
    private boolean dirArr3 = false;
    private boolean dirAba3 = false;
    private boolean activo3 = false;
    private int cuerpos3;
    private int manzanasCom3;
    private Image cuerpo3;
    private Image cabeza3;
    private Image cola3;
    private String nombreCola3;
    
  //jugador cuatro
    private final int x4[] = new int[maximoCuerpos];
    private final int y4[] = new int[maximoCuerpos];
    private boolean dirIzq4 = false;
    private boolean dirDer4 = true;
    private boolean dirArr4 = false;
    private boolean dirAba4 = false;
    private boolean activo4 = false;
    private int cuerpos4;
    private int manzanasCom4;
    private Image cuerpo4;
    private Image cabeza4;
    private Image cola4;
    private String nombreCola4;
    
    private int manzana_x;
    private int manzana_y;
    private Image manzana;
    
    private int modo = 1;
    private int cantJug;
    private int ganador;
    
    private boolean inGame = true;

    private Timer timer;
    
    
    

    public tablero() {
        
    	iniCampo();
    }
    
    private void iniCampo() {

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);
        setDoubleBuffered(true);

        setPreferredSize(new Dimension(anchoCampo, altoCampo));
        iniJuego();
        cargarImg();
        
    }

    private void cargarImg() {
    	
    	ImageIcon iia = new ImageIcon("src/multimedia/manzana.png");
        manzana = iia.getImage();
        
        if(activo1) {
        
        	ImageIcon iicu = new ImageIcon("src/multimedia/rojo.png");
        	cuerpo = iicu.getImage();

        	ImageIcon iica = new ImageIcon("src/multimedia/verde.png");
        	cabeza = iica.getImage();
        }
        
        if(activo2) {
        
        	ImageIcon iicu2 = new ImageIcon("src/multimedia/morado.png");
        	cuerpo2 = iicu2.getImage();

        	ImageIcon iica2 = new ImageIcon("src/multimedia/anaranjado.png");
        	cabeza2 = iica2.getImage();
        }
        
        if(activo3) {
          
          ImageIcon iicu3 = new ImageIcon("src/multimedia/blanco.png");
          cuerpo3 = iicu3.getImage();

          ImageIcon iica3 = new ImageIcon("src/multimedia/azul.png");
          cabeza3 = iica3.getImage();
         }
        
        if(activo4) {
            
            ImageIcon iicu4 = new ImageIcon("src/multimedia/gris.png");
            cuerpo4 = iicu4.getImage();

            ImageIcon iica4 = new ImageIcon("src/multimedia/acua.png");
            cabeza4 = iica4.getImage();
        }
        
    }

    private void iniJuego() {

    	cuerpos = 2;
    	cuerpos2 = 2;
    	cuerpos3 = 2;
    	cuerpos4 = 2;
    	
    	if(modo == 2) {
    		activo2 = true;
    	}
    	
    	if(modo == 3) {
    		activo2 = true;
    		activo3 = true;
    		cantJug = 3;
    	}
    	
    	if(modo == 4) {
    		activo2 = true;
    		activo3 = true;
    		activo4 = true;
    		cantJug = 4;
    	}
    	
    	if(activo1) {
        for (int z = 0; z < cuerpos; z++) {
            	x[z] = 50 - z * 10;
            	y[z] = 50;
        	}
    	}
        
        if(activo2) {
        	for (int z = 0; z < cuerpos2; z++) {
        		x2[z] = 100 - z * 10;
        		y2[z] = 100;
        	}
        }
        
        if(activo3) {
            for (int z = 0; z < cuerpos3; z++) {
                x3[z] = 150 - z * 10;
                y3[z] = 150;
           }
        }
        if(activo4) {
            for (int z = 0; z < cuerpos4; z++) {
                x4[z] = 200 - z * 10;
                y4[z] = 200;
           }
        }
        
        ubManzana();

        timer = new Timer(retrasoEnMov, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        dibujar(g);
    }
    
    private void dibujar(Graphics g) {
        
        if (inGame) {

            g.drawImage(manzana, manzana_x, manzana_y, this);
            
            if(activo1) {
            	for (int z = 0; z < cuerpos; z++) {
            		if (z == 0) {
            			g.drawImage(cabeza, x[z], y[z], this);
            			//                } else if (z == (cuerpos - 1)){
            			//                	g.drawImage(cola, x[z], y[z], this);
            		}else {
            			g.drawImage(cuerpo, x[z], y[z], this);
            		}
            	}
            }

            if(activo2) {
            	for (int z = 0; z < cuerpos2; z++) {
            		if (z == 0) {
            			g.drawImage(cabeza2, x2[z], y2[z], this);
            			//                } else if (z == (cuerpos2 - 1)){
            			//                	g.drawImage(cola2, x2[z], y2[z], this);
            		} else {
            			g.drawImage(cuerpo2, x2[z], y2[z], this);
            		}
            	}
            }
            
            if(activo3) {
            	for (int z = 0; z < cuerpos3; z++) {
            		if (z == 0) {
            			g.drawImage(cabeza3, x3[z], y3[z], this);
            			g.drawImage(cola3, x3[z], y3[z], this);
            		}else {
            			g.drawImage(cuerpo3, x3[z], y3[z], this);
            		}
            	}
            }
            
            if(activo4) {
            	for (int z = 0; z < cuerpos4; z++) {
            		if (z == 0) {
            			g.drawImage(cabeza4, x4[z], y4[z], this);
            			g.drawImage(cola4, x4[z], y4[z], this);
            		}else {
            			g.drawImage(cuerpo4, x4[z], y4[z], this);
            		}
            	}
            }

            Toolkit.getDefaultToolkit().sync();

        } else {

            gameOver(g);
        }        
    }

    private void gameOver(Graphics g) {
        String msg = "";
        if(modo == 1) {
        	msg = "Game Over - Tu puntuaci�n es: " + manzanasCom;
    	}else if(modo == 2) {
    		msg = "Game Over - Jugador " + ganador + " gana.";
    	}else if(modo > 2) {
    		if(activo1) {
    			msg = "Game Over - Jugador uno gana.";
    		}else if(activo2) {
    			msg = "Game Over - Jugador dos gana.";
    		}else if(activo3) {
    			msg = "Game Over - Jugador tres gana.";
    		}
    		else if(activo4) {
    			msg = "Game Over - Jugador cuatro gana.";
    		}
    	}
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (anchoCampo - metr.stringWidth(msg)) / 2, altoCampo / 2);
    }

    private void verificarManzanaEst() {

        if ((x[0] == manzana_x) && (y[0] == manzana_y)) {
        	
        	manzanasCom++;
        	cuerpos++;
        	ubManzana();
        }else if ((x2[0] == manzana_x) && (y2[0] == manzana_y)) {
        	
        	manzanasCom2++;
        	cuerpos2++;
        	ubManzana();
        }else if ((x3[0] == manzana_x) && (y3[0] == manzana_y)) {
        	
        	manzanasCom3++;
        	cuerpos3++;
        	ubManzana();
        }else if ((x4[0] == manzana_x) && (y4[0] == manzana_y)) {
        	
        	manzanasCom4++;
        	cuerpos4++;
        	ubManzana();
        }
    }

    private void movimiento() {

        for (int z = cuerpos; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (dirIzq) {
            x[0] -= cuerpoTamanho;
        }

        if (dirDer) {
            x[0] += cuerpoTamanho;
        }

        if (dirArr) {
            y[0] -= cuerpoTamanho;
        }

        if (dirAba) {
            y[0] += cuerpoTamanho;
        }
        
        for (int z = cuerpos2; z > 0; z--) {
            x2[z] = x2[(z - 1)];
            y2[z] = y2[(z - 1)];
        }

        if (dirIzq2) {
            x2[0] -= cuerpoTamanho;
        }

        if (dirDer2) {
            x2[0] += cuerpoTamanho;
        }

        if (dirArr2) {
            y2[0] -= cuerpoTamanho;
        }

        if (dirAba2) {
            y2[0] += cuerpoTamanho;
        }
        
        for (int z = cuerpos3; z > 0; z--) {
            x3[z] = x3[(z - 1)];
            y3[z] = y3[(z - 1)];
        }

        if (dirIzq3) {
            x3[0] -= cuerpoTamanho;
        }

        if (dirDer3) {
            x3[0] += cuerpoTamanho;
        }

        if (dirArr3) {
            y3[0] -= cuerpoTamanho;
        }

        if (dirAba3) {
            y3[0] += cuerpoTamanho;
        }
        
        for (int z = cuerpos4; z > 0; z--) {
            x4[z] = x4[(z - 1)];
            y4[z] = y4[(z - 1)];
        }

        if (dirIzq4) {
            x4[0] -= cuerpoTamanho;
        }

        if (dirDer4) {
            x4[0] += cuerpoTamanho;
        }

        if (dirArr4) {
            y4[0] -= cuerpoTamanho;
        }

        if (dirAba4) {
            y4[0] += cuerpoTamanho;
        }
    }

    private void verificarContacto() {
    	if(modo == 1) {
    		if(activo1) {
    			for (int z = cuerpos; z > 0; z--) {

    				if(manzanasCom >= 1) {
    					if ((z > 0) && (x[0] == x[z]) && (y[0] == y[z])) {
    						inGame = false;
    					}
    				}
    			}
    			if (y[0] >= altoCampo) {
    				inGame = false;
    			}

    			if (y[0] < 0) {
    				inGame = false;
    			}

    			if (x[0] >= anchoCampo) {
    				inGame = false;
    			}

    			if (x[0] < 0) {
    				inGame = false;
    			}

    			if (!inGame) {
    				timer.stop();
    			}
    		}
    		
    	}else if(modo == 2) {
    		if(activo1) {
    			for (int z = cuerpos; z > 0; z--) {

    				if(manzanasCom2 >= 1) {
    					if ((z > 0) && (x[0] == x[z]) && (y[0] == y[z])) {
    						ganador = 2;
    						inGame = false;
    					}else if((z > 0) && (x[0] == x2[z]) && (y[0] == y2[z])) {
    						ganador = 2;
    						inGame = false;
    					}
    				}
    			}
    			if (y[0] >= altoCampo) {
    				ganador = 2;
    				inGame = false;
    			}

    			if (y[0] < 0) {
    				ganador = 2;
    				inGame = false;
    			}

    			if (x[0] >= anchoCampo) {
    				ganador = 2;
    				inGame = false;
    			}

    			if (x[0] < 0) {
    				ganador = 2;
    				inGame = false;
    			}

    			if (!inGame) {
    				timer.stop();
    			}
    		}

    		if(activo2) {
    			for (int w = cuerpos2; w > 0; w--) {

    				if(manzanasCom >= 1) {
    					if ((w > 0) && (x2[0] == x2[w]) && (y2[0] == y2[w])) {
    						ganador = 1;
    						inGame = false;
    					}else if((w > 0) && (x2[0] == x[w]) && (y2[0] == y[w])) {
    						ganador = 1;
    						inGame = false;
    					}
    				}
    			}

    			if (y2[0] >= altoCampo) {
    				ganador = 1;
    				inGame = false;
    			}

    			if (y2[0] < 0) {
    				ganador = 1;
    				inGame = false;
    			}

    			if (x2[0] >= anchoCampo) {
    				ganador = 1;
    				inGame = false;
    			}

    			if (x2[0] < 0) {
    				ganador = 1;
    				inGame = false;
    			}

    			if (!inGame) {
    				timer.stop();
    			}
    		}

    	}else if(modo > 2) {

    		if(activo1) {
    			for (int z = cuerpos; z > 0; z--) {

    				if(manzanasCom >= 1 || manzanasCom2 >= 1 || manzanasCom3 >= 1 || manzanasCom4 >= 1) {
    					if ((z > 0) && (x[0] == x[z]) && (y[0] == y[z])) {
    						activo1 = false;
    						cantJug = cantJug - 1;
    					}else if((z > 0) && (x[0] == x2[z]) && (y[0] == y2[z])) {
    						activo1 = false;
    						cantJug = cantJug - 1;
    					}else if((z > 0) && (x[0] == x3[z]) && (y[0] == y3[z])) {
    						activo3 = false;
    						cantJug = cantJug - 1;	
    					}else if((z > 0) && (x[0] == x4[z]) && (y[0] == y4[z])) {
    						activo1 = false;
    						cantJug = cantJug - 1;
    						
    					}
    				}
    			}
    			if (y[0] >= altoCampo) {
    				activo1 = false;
    				cantJug = cantJug - 1;
    			}

    			if (y[0] < 0) {
    				activo1 = false;
    				cantJug = cantJug - 1;
    			}

    			if (x[0] >= anchoCampo) {
    				activo1 = false;
    				cantJug = cantJug - 1;
    			}

    			if (x[0] < 0) {
    				activo1 = false;
    				cantJug = cantJug - 1;
    			}

    			if (!inGame) {
    				timer.stop();
    			}
    			
    			if(cantJug == 1) {
    				inGame = false;
    			}
    		}

    		if(activo2) {
    			for (int w = cuerpos2; w > 0; w--) {

    				if(manzanasCom >= 1 || manzanasCom2 >= 1 || manzanasCom3 >= 1 || manzanasCom4 >= 1) {
    					if ((w > 0) && (x2[0] == x2[w]) && (y2[0] == y2[w])) {
    						activo2 = false;
    						cantJug = cantJug - 1;
    					}else if((w > 0) && (x2[0] == x[w]) && (y2[0] == y[w])) {
    						activo2 = false;
    						cantJug = cantJug - 1;
    					}else if((w > 0) && (x2[0] == x3[w]) && (y2[0] == y3[w])) {
    						activo3 = false;
    						cantJug = cantJug - 1;    						
    					}else if((w > 0) && (x2[0] == x4[w]) && (y2[0] == y4[w])) {
    						activo2 = false;
    						cantJug = cantJug - 1;
    						
    					}
    				}
    			}

    			if (y2[0] >= altoCampo) {
    				activo2 = false;
    				cantJug = cantJug - 1;
    			}

    			if (y2[0] < 0) {
    				activo2 = false;
    				cantJug = cantJug - 1;
    			}

    			if (x2[0] >= anchoCampo) {
    				activo2 = false;
    				cantJug = cantJug - 1;
    			}

    			if (x2[0] < 0) {
    				activo2 = false;
    				cantJug = cantJug - 1;
    			}

    			if (!inGame) {
    				timer.stop();
    			}
    			
    			if(cantJug == 1) {
    				inGame = false;
    			}
    		}
    		
    		if(activo3) {
    			for (int z = cuerpos3; z > 0; z--) {

    				if(manzanasCom >= 1 || manzanasCom2 >= 1 || manzanasCom3 >= 1 || manzanasCom4 >= 1) {
    					if ((z > 0) && (x3[0] == x3[z]) && (y3[0] == y3[z])) {
    						activo3 = false;
    						cantJug = cantJug - 1;    				
    					}else if((z > 0) && (x3[0] == x[z]) && (y3[0] == y[z])) {
    						activo3 = false;
    						cantJug = cantJug - 1;    						
    					}else if((z > 0) && (x3[0] == x2[z]) && (y3[0] == y2[z])) {
    						activo3 = false;
    						cantJug = cantJug - 1;	
    					}else if((z > 0) && (x3[0] == x4[z]) && (y3[0] == y4[z])) {
    						activo3 = false;
    						cantJug = cantJug - 1;
    						
    					}
    				}
    			}
    			if (y3[0] >= altoCampo) {
    				activo3 = false;
    				cantJug = cantJug - 1;
    			}

    			if (y3[0] < 0) {
    				activo3 = false;
    				cantJug = cantJug - 1;
    			}

    			if (x3[0] >= anchoCampo) {
    				activo3 = false;
    				cantJug = cantJug - 1;
    			}

    			if (x3[0] < 0) {
    				activo3 = false;
    				cantJug = cantJug - 1;
    			}

    			if (!inGame) {
    				timer.stop();
    			}
    			
    			if(cantJug == 1) {
    				inGame = false;
    			}
    			
    		}

    		if(activo4) {
    			for (int z = cuerpos4; z > 0; z--) {

    				if(manzanasCom >= 1 || manzanasCom2 >= 1 || manzanasCom3 >= 1 || manzanasCom4 >= 1) {
    					if ((z > 0) && (x4[0] == x4[z]) && (y4[0] == y4[z])) {
    						activo4 = false;
    						cantJug = cantJug - 1;    				
    					}else if((z > 0) && (x4[0] == x[z]) && (y4[0] == y[z])) {
    						activo4 = false;
    						cantJug = cantJug - 1;    						
    					}else if((z > 0) && (x4[0] == x2[z]) && (y4[0] == y2[z])) {
    						activo4 = false;
    						cantJug = cantJug - 1;    						
    					}else if((z > 0) && (x4[0] == x3[z]) && (y4[0] == y3[z])) {
    						activo3 = false;
    						cantJug = cantJug - 1;
    						
    					}
    				}
    			}
    			if (y4[0] >= altoCampo) {
    				activo4 = false;
    				cantJug = cantJug - 1;
    			}

    			if (y4[0] < 0) {
    				activo4 = false;
    				cantJug = cantJug - 1;
    			}

    			if (x4[0] >= anchoCampo) {
    				activo4 = false;
    				cantJug = cantJug - 1;
    			}

    			if (x4[0] < 0) {
    				activo4 = false;
    				cantJug = cantJug - 1;
    			}

    			if (!inGame) {
    				timer.stop();
    			}
    			
    			if(cantJug == 1) {
    				inGame = false;
    			}
    			
    		}
    		
    		
    	}
    }

    private void ubManzana() {

        int r = (int) (Math.random() * manzanaUbPos);
        manzana_x = ((r * cuerpoTamanho));

        r = (int) (Math.random() * manzanaUbPos);
        manzana_y = ((r * cuerpoTamanho));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {

        	verificarManzanaEst();
        	verificarContacto();
            movimiento();
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!dirDer)) {
            	dirIzq = true;
            	dirArr = false;
            	dirAba = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!dirIzq)) {
            	dirDer = true;
            	dirArr = false;
            	dirAba = false;
            }

            if ((key == KeyEvent.VK_UP) && (!dirAba)) {
            	dirArr = true;
                dirDer = false;
                dirIzq = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!dirArr)) {
            	dirAba = true;
                dirDer = false;
                dirIzq = false;
            }
            
            if ((key == KeyEvent.VK_A) && (!dirDer2)) {
            	dirIzq2 = true;
            	dirArr2 = false;
            	dirAba2 = false;
            }

            if ((key == KeyEvent.VK_D) && (!dirIzq2)) {
            	dirDer2 = true;
            	dirArr2 = false;
            	dirAba2 = false;
            }

            if ((key == KeyEvent.VK_W) && (!dirAba2)) {
            	dirArr2 = true;
                dirDer2 = false;
                dirIzq2 = false;
            }

            if ((key == KeyEvent.VK_S) && (!dirArr2)) {
            	dirAba2 = true;
                dirDer2 = false;
                dirIzq2 = false;
            }
            
            if ((key == KeyEvent.VK_J) && (!dirDer3)) {
            	dirIzq3 = true;
            	dirArr3 = false;
            	dirAba3 = false;
            }

            if ((key == KeyEvent.VK_L) && (!dirIzq3)) {
            	dirDer3 = true;
            	dirArr3 = false;
            	dirAba3 = false;
            }

            if ((key == KeyEvent.VK_I) && (!dirAba3)) {
            	dirArr3 = true;
                dirDer3 = false;
                dirIzq3 = false;
            }

            if ((key == KeyEvent.VK_K) && (!dirArr3)) {
            	dirAba3 = true;
                dirDer3 = false;
                dirIzq3 = false;
            }
            
            if ((key == KeyEvent.VK_4) && (!dirDer4)) {
            	dirIzq4 = true;
            	dirArr4 = false;
            	dirAba4 = false;
            }

            if ((key == KeyEvent.VK_6) && (!dirIzq4)) {
            	dirDer4 = true;
            	dirArr4 = false;
            	dirAba4 = false;
            }

            if ((key == KeyEvent.VK_8) && (!dirAba4)) {
            	dirArr4 = true;
                dirDer4 = false;
                dirIzq4 = false;
            }

            if ((key == KeyEvent.VK_5) && (!dirArr4)) {
            	dirAba4 = true;
                dirDer4 = false;
                dirIzq4 = false;
            }
        }
    }
}
