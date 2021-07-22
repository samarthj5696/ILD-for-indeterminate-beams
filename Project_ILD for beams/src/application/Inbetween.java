	package application;
	public class Inbetween {
	
	public static double[][] main(int n, int K,double[] L,String shearormoment,String endsupports) {
		
				double[] valueofy ;
				//Taking Values for graph class
				double ar[][] = new double[2][];
				ar[0]=new double[101*n+101];			
				ar[1]=new double[n+1];
				
				for (int e = 0; e< n+1; e++) {
					ar[1][e] = L[e];
				}
				
		// Moment &	Pinned	
		if(shearormoment.length()==6 && endsupports.length()==18) {

			System.out.println("Pinned Support");
	
			/*
			// number of beam input from user
			 
			System.out.println("Enter number of beam : ");
			int n = sc.nextInt(); 
			    
			System.out.println("Enter the beam number on which point of interest lies is :  ");
			int K = sc.nextInt(); 
			
			 L = new double[n+1]; 
		
			// Distance between supports 
			for (int i = 0; i <n+1 ; i++) {
				 
						if (i!=K-1 && i!=(K)) {
						if (i<K-1) {
							System.out.println("Enter Length of " + (i+1) + " beam : ");
							L[i] =  sc.nextFloat();} 
						else{
							System.out.println("Enter Length of " + (i) + " beam : ");
							L[i] =  sc.nextFloat(); }}
					
					
						if(i==K-1) {
							System.out.println("Enter distance of point of interest from left nearest support: ");
							L[i] = sc.nextInt();
						}
						if(i==K) {
							System.out.println("Enter distance of point of interest from right nearest support: ");
							L[i] = sc.nextInt(); }		
			}
			
			*/
			
			//Local Matrix
			double [][][]local = new double[n+1][4][4];
			for (int i = 0; i<n+1; i++) {
				local[i][0][0] = 12/(L[i]*L[i]*L[i]);
				local[i][0][1] = 6/((L[i])*(L[i]));
				local[i][0][2] = -12/(L[i]*L[i]*L[i]);
				local[i][0][3] = 6/(L[i]*L[i]);
				local[i][1][0] = 6/(L[i]*L[i]);
				local[i][1][1] = 4/(L[i]);
				local[i][1][2] = -6/(L[i]*L[i]);
				local[i][1][3] = 2/L[i];
				local[i][2][0] = -12/(L[i]*L[i]*L[i]);
				local[i][2][1] = -6/(L[i]*L[i]);
				local[i][2][2] = 12/(L[i]*L[i]*L[i]);
				local[i][2][3] = -6/(L[i]*L[i]);
				local[i][3][0] = 6/(L[i]*L[i]);
				local[i][3][1] = 2/(L[i]);
				local[i][3][2] = -6/(L[i]*L[i]);
				local[i][3][3] = 4/L[i];			
			}
			
			//Printing Local Matrix
			System.out.println();
			System.out.println("The Local Matrix");
			
				for (int i = 0; i < n+1; i++) {
					for (int j = 0; j < 4; j++) {
						for (int k = 0; k < 4; k++) {System.out.print(local[i][j][k] + "  ");} System.out.println();
					}System.out.println();
				}

		
			//Global Matrix
			float [][] global= new float[2*n+5][2*n+5];
			
				for (int i = 0; i < n+1; i++) {
					int k;
					int l;
						if (i<=K) {
							k = 2*i;
							l=2*i;
						}	
						else {
							k = 2*i+1;
							l=2*i+1;
						}
						for (int m = 0; m < 4; m++) {
							
								for (int r = 0; r < 4; r++) {
									if (k==2*K+1&&i>=K) {
										k=k+1;
										}
									if (l==2*K+1&&i>=K) {
										l=l+1;
										}
								
									global[k][l] += local[i][m][r];
									
									l++;
								} 
								if (i<=K) {
									l=2*i;	
								}	
								else {
									l=2*i+1;
								}
							k++;
						}
				}
		
				
			//Printing Global Matrix
			System.out.println();
			System.out.println("The Global Matrix");
			for (int i = 0; i < 2*n+5; i++) {
				for (int j = 0; j < 2*n+5; j++) {
					System.out.print(global[i][j] + "    ");
				
				} System.out.println();
			}
		
			 
			// GlobalK1 Matrix
			float globalk1[][] = new float[n+4][n+4];
			int l=0;
			int q=0;
			for (int i = 0; i <n+4; i++) {
				
					if (i<K) {l=2*i+1;}
					if (i==K) {l=2*i;}
					if (i==K+1) {l=2*i-1;}
					if (i>=K+2) {l=2*i-2;}
				
			
					for (int j = 0; j < n+4; j++) {
						if (j<K) {q=2*j+1;}
						if (j==K) {q=2*j;}
						if (j==K+1) {q=2*j-1;}
						if (j>=K+2) {q=2*j-2;}
						
						globalk1[i][j] = global[l][q];
					
					}
			}

			// Printing GlobalK1 Matrix
			System.out.println();
			System.out.println("The GlobalK1 Matrix");
			for (int i = 0; i <n+4; i++) {
					for (int j = 0; j < n+4; j++) {
						System.out.print(globalk1[i][j] + "    ");
					} System.out.println();
			}
		
			// Inverse of GlobalK1 Matrix 
			float inverse[][] = new float[n+4][n+4];
			inverse = Inverse.invert(globalk1);
			System.out.println();
			System.out.println("Inverse of globalK1 Matrix");
			
			// Printing of Inverse of GlobalK1 Matrix 
			for (int i = 0; i < n+4; i++) {
				for (int j = 0; j < n+4; j++) {
					System.out.print(inverse[i][j]);
				} System.out.println();
			} System.out.println();
			
			// Defining Force Matrix
			float[] force= new float[2*n+5];
			force[2*K+1]=1;
			force[2*K+2]=-1;
			
			//Defining Displacement Matrix
			float[] displacement= new float[2*n+5];

			System.out.println("Displacements");
			for (int i = 0; i < n+4; i++) {
				
					for (int j = 0; j < n+4; j++) {
						if (i<K) {
							if (j<K) 	{displacement[2*i+1]+=inverse[i][j]*force[2*j+1];}
							if (j==K)	{displacement[2*i+1]+=inverse[i][j]*force[2*j];}
							if (j==K+1) {displacement[2*i+1]+=inverse[i][j]*force[2*j-1];}
							if (j>=K+2) {displacement[2*i+1]+=inverse[i][j]*force[2*j-2];}
						
						}
				
						if (i==K) {
							if (j<K)    {displacement[2*i]+=inverse[i][j]*force[2*j+1];}
							if (j==K)   {displacement[2*i]+=inverse[i][j]*force[2*j];}
							if (j==K+1) {displacement[2*i]+=inverse[i][j]*force[2*j-1];}
							if (j>=K+2) {displacement[2*i]+=inverse[i][j]*force[2*j-2];}
						}
					
						if (i==K+1) {
							if (j<K)    {displacement[2*i-1]+=inverse[i][j]*force[2*j+1];}
							if (j==K)   {displacement[2*i-1]+=inverse[i][j]*force[2*j];}
							if (j==K+1) {displacement[2*i-1]+=inverse[i][j]*force[2*j-1];}
							if (j>=K+2) {displacement[2*i-1]+=inverse[i][j]*force[2*j-2];}
						}
					
						if (i>=K+2) {
							if (j<K)    {displacement[2*i-2]+=inverse[i][j]*force[2*j+1];}
							if (j==K)   {displacement[2*i-2]+=inverse[i][j]*force[2*j];}
							if (j==K+1) {displacement[2*i-2]+=inverse[i][j]*force[2*j-1];}
							if (j>=K+2) {displacement[2*i-2]+=inverse[i][j]*force[2*j-2];}					
						}
				 
					}
			}

			//Printing Displacement
			for (int i = 0; i < 2*n+5; i++) {
			System.out.println("Displacement : "+i+" "+displacement[i]);	
			} System.out.println();
			
			force[2*K+1]=0;
			force[2*K+2]=0;
			//Calculation all Forces
					for (int i = 0; i < 2*n+5; i++) {
						for (int j = 0; j < 2*n+5; j++) {
							force[i] += global[i][j]*displacement[j];
						}
					}
					
			//Printing all forces
					System.out.println("Forces :");
					for (int i = 0; i < 2*n+5; i++) {
						System.out.println("Force correspond to "+ (i+1) + " : "+ force[i] + "  Displacement : "+ displacement[i]);
					}System.out.println();
					
			//Storing vertical force
					System.out.println("Verical Forces :");
					float verticalforce[]=new float[n+2];
					int j=0;
					for (int i = 0; i < n+2; i++) {
						if (i<=K) {verticalforce[i]= force[2*j];}
						else 	 {verticalforce[i]= force[2*j+1];}
						j++;
					}
		
			//Printing Vertical Force
			for (int i = 0; i < n+2; i++) {
				System.out.println(verticalforce[i]);
			} System.out.println();
		
		
	//Finding Final Equation
			
			//Value of x
			float valueofx[] = new float[n+2];
			valueofx[0]=0;
			for (int i = 0; i < n+1; i++) {
				for (int m = 0; m < i+1; m++) {
					valueofx[i+1]+=L[m];
					
					}System.out.println("Value of x: "+valueofx[i+1]);
			}
			
			float [][][] array1= new float[n+1][4][4];		
			
			for (int i = 0; i < n+1; i++) {
				array1[i][0][0] = valueofx[i]*valueofx[i]*valueofx[i];
				array1[i][0][1] = valueofx[i]*valueofx[i];
				array1[i][0][2] = valueofx[i];
				array1[i][0][3] = 1;
				array1[i][1][0] = 3*valueofx[i]*valueofx[i];
				array1[i][1][1] = 2*valueofx[i];
				array1[i][1][2] = 1;
				array1[i][1][3] = 0;
				array1[i][2][0] = valueofx[i+1]*valueofx[i+1]*valueofx[i+1];
				array1[i][2][1] = valueofx[i+1]*valueofx[i+1];
				array1[i][2][2] = valueofx[i+1];
				array1[i][2][3] = 1;
				array1[i][3][0] = 3*valueofx[i+1]*valueofx[i+1];
				array1[i][3][1] = 2*valueofx[i+1];
				array1[i][3][2] = 1;
				array1[i][3][3] = 0;	
			}

			float inv[][] = new float[n+4][n+4];
			float abcd [][] = new float[n+1][4];
			
			for (int i = 0; i < n+1; i++) {
				float [][] matrix = {{array1[i][0][0],array1[i][0][1],array1[i][0][2],array1[i][0][3]} , {array1[i][1][0],array1[i][1][1],array1[i][1][2],array1[i][1][3]} , {array1[i][2][0],array1[i][2][1],array1[i][2][2],array1[i][2][3]} , {array1[i][3][0],array1[i][3][1],array1[i][3][2],array1[i][3][3]}};
				inv = Inverse.invert(matrix);
				
					for (int r = 0; r < 4; r++) {
						for (int r2 = 0; r2 < 4; r2++) {
							System.out.print("Inverse : ");
							System.out.print(inv[r][r2]+"  ");
						} System.out.println();
					}System.out.println();
					
							for (int m2 = 0; m2 < 4; m2++) {
								int r=2*i;
								if (r>2*K) {r++;}
								for (int m3 = 0; m3 < 4; m3++) {
									abcd[i][m2]+= inv[m2][m3]*displacement[r];
									System.out.println("ABCD value for "+ i+"and "+r+" is :"+abcd[i][m2]);
									if (r==2*K&i==K) {r++;}
									r++;
									
								}
							}
		
			}
			
			System.out.println();
			float Scalefactor = displacement[2*K+1]-displacement[2*K+2];
			System.out.println("Values of Scalefactor: " + Scalefactor);  System.out.println(); 
			
			valueofy = new double[101*n+101] ;
			double X1;
			int r=0;
			
			for (int i1 = 0; i1 < n+1; i1++) {
					 X1= valueofx[i1];
					 
					 while (X1<=valueofx[i1+1]) {					
							valueofy[r] = (X1*X1*X1*abcd[i1][0]+X1*X1*abcd[i1][1]+X1*abcd[i1][2]+abcd[i1][3])/Scalefactor;
							System.out.println("Values of x "+X1+" for y: " + valueofy[r]);	
							X1=X1+0.01*L[i1];
							r++;
							
					}
				
			}
			
			for (int e= 0; e < 101*n+101; e++) {
				ar[0][e] = valueofy[e];
				
			}
			
		
		
		}
		
		// Moment &	One End Fixed
		if(shearormoment.length()==6 && endsupports.length()==22) {

			System.out.println("one end Fixed Support");
			
			/*
			// number of beam input from user
			
			System.out.println("Enter number of beam : ");
			int n = sc.nextInt(); 
			    
			System.out.println("Enter the beam number on which point of interest lies is :  ");
			int K = sc.nextInt(); 
			
			 L = new double[n+1]; 
		
			// Distance between supports 
			for (int i = 0; i <n+1 ; i++) {
				 
						if (i!=K-1 && i!=(K)) {
						if (i<K-1) {
							System.out.println("Enter Length of " + (i+1) + " beam : ");
							L[i] =  sc.nextFloat();} 
						else{
							System.out.println("Enter Length of " + (i) + " beam : ");
							L[i] =  sc.nextFloat(); }}
					
					
						if(i==K-1) {
							System.out.println("Enter distance of point of interest from left nearest support: ");
							L[i] = sc.nextInt();
						}
						if(i==K) {
							System.out.println("Enter distance of point of interest from right nearest support: ");
							L[i] = sc.nextInt(); }		
			}
			*/
			
			//Local Matrix
			double [][][]local = new double[n+1][4][4];
			for (int i = 0; i<n+1; i++) {
				local[i][0][0] = 12/(L[i]*L[i]*L[i]);
				local[i][0][1] = 6/((L[i])*(L[i]));
				local[i][0][2] = -12/(L[i]*L[i]*L[i]);
				local[i][0][3] = 6/(L[i]*L[i]);
				local[i][1][0] = 6/(L[i]*L[i]);
				local[i][1][1] = 4/(L[i]);
				local[i][1][2] = -6/(L[i]*L[i]);
				local[i][1][3] = 2/L[i];
				local[i][2][0] = -12/(L[i]*L[i]*L[i]);
				local[i][2][1] = -6/(L[i]*L[i]);
				local[i][2][2] = 12/(L[i]*L[i]*L[i]);
				local[i][2][3] = -6/(L[i]*L[i]);
				local[i][3][0] = 6/(L[i]*L[i]);
				local[i][3][1] = 2/(L[i]);
				local[i][3][2] = -6/(L[i]*L[i]);
				local[i][3][3] = 4/L[i];			
			}
		
			//Printing Local Matrix
			System.out.println();
			System.out.println("The Local Matrix");
			
				for (int i = 0; i < n+1; i++) {
					for (int j = 0; j < 4; j++) {
						for (int k = 0; k < 4; k++) {System.out.print(local[i][j][k] + "  ");} System.out.println();
					}System.out.println();
				}

		
			//Global Matrix
			float [][] global= new float[2*n+5][2*n+5];
			
				for (int i = 0; i < n+1; i++) {
					int k;
					int l;
						if (i<=K) {
							k = 2*i;
							l=2*i;
						}	
						else {
							k = 2*i+1;
							l=2*i+1;
						}
								for (int m = 0; m < 4; m++) {
							
										for (int r = 0; r < 4; r++) {
												if (k==2*K+1&&i>=K) {
														k=k+1;
													}
													if (l==2*K+1&&i>=K) {
															l=l+1;
													}
											global[k][l] += local[i][m][r];
											l++;
										} 
											if (i<=K) {
													l=2*i;	
											}	
											else {
												l=2*i+1;
											}
									k++;
								}
				}
		
				
			//Printing Global Matrix
			System.out.println();
			System.out.println("The Global Matrix");
			for (int i = 0; i < 2*n+5; i++) {
				for (int j = 0; j < 2*n+5; j++) {
					System.out.print(global[i][j] + "    ");
				
				} System.out.println();
			}
		
			
			 
			// GlobalK1 Matrix
			float globalk1[][] = new float[n+3][n+3];
			int l=0;
			int q=0;
			for (int i = 0; i <n+3; i++) {
				
					if (i<K-1) {l=2*i+3;}
					if (i==K-1) {l=2*i+2;}
					if (i==K) {l=2*i+1;}
					if (i>=K+1) {l=2*i;}
					
			
					for (int j = 0; j < n+3; j++) {
						if (j<K-1) {q=2*j+3;}
						if (j==K-1) {q=2*j+2;}
						if (j==K) {q=2*j+1;}
						if (j>=K+1) {q=2*j;}
					
						globalk1[i][j] = global[l][q];
						
					}
			}

			// Printing GlobalK1 Matrix
			System.out.println();
			System.out.println("The GlobalK1 Matrix");
			for (int i = 0; i <n+3; i++) {
					for (int j = 0; j < n+3; j++) {
						System.out.print(globalk1[i][j] + "    ");
					} System.out.println();
			}
		
			// Inverse of GlobalK1 Matrix 
			float inverse[][] = new float[n+3][n+3];
			inverse = Inverse.invert(globalk1);
			System.out.println();
			System.out.println("Inverse of globalK1 Matrix");
			
			// Printing of Inverse of GlobalK1 Matrix 
			for (int i = 0; i < n+3; i++) {
				for (int j = 0; j < n+3; j++) {
					System.out.print(inverse[i][j]);
				} System.out.println();
			} System.out.println();
			
			// Defining Force Matrix
			float[] force= new float[2*n+5];
			force[2*K+1]=1;
			force[2*K+2]=-1;
			
			
			//Defining Displacement Matrix
			float[] displacement= new float[2*n+5];
	
			System.out.println("Displacements");
			for (int i = 0; i < n+3; i++) {
				
				for (int j = 0; j < n+3; j++) {
					if (i<K-1) {
						if (j<K-1) 	{displacement[2*i+3]+=inverse[i][j]*force[2*j+3];}
						if (j==K-1)	{displacement[2*i+3]+=inverse[i][j]*force[2*j+2];}
						if (j==K) {displacement[2*i+3]+=inverse[i][j]*force[2*j+1];}
						if (j>=K+1) {displacement[2*i+3]+=inverse[i][j]*force[2*j];}
					
					}
			
					if (i==K-1) {
						if (j<K-1)    {displacement[2*i+2]+=inverse[i][j]*force[2*j+3];}
						if (j==K-1)   {displacement[2*i+2]+=inverse[i][j]*force[2*j+2];}
						if (j==K) {displacement[2*i+2]+=inverse[i][j]*force[2*j+1];}
						if (j>=K+1) {displacement[2*i+2]+=inverse[i][j]*force[2*j];}
					}
				
					if (i==K) {
						if (j<K-1)    {displacement[2*i+1]+=inverse[i][j]*force[2*j+3];}
						if (j==K-1)   {displacement[2*i+1]+=inverse[i][j]*force[2*j+2];}
						if (j==K) {displacement[2*i+1]+=inverse[i][j]*force[2*j+1];}
						if (j>=K+1) {displacement[2*i+1]+=inverse[i][j]*force[2*j];}
					}
				
					if (i>=K+1) {
						if (j<K-1)    {displacement[2*i]+=inverse[i][j]*force[2*j+3];}
						if (j==K-1)   {displacement[2*i]+=inverse[i][j]*force[2*j+2];}
						if (j==K) {displacement[2*i]+=inverse[i][j]*force[2*j+1];}
						if (j>=K+1) {displacement[2*i]+=inverse[i][j]*force[2*j];}					
					}
			 
				}
		}
			
			//Printing Displacement
			for (int i = 0; i < 2*n+5; i++) {
			System.out.println("Displacement : "+i+" "+displacement[i]);	
			} System.out.println();
		
			force[2*K+1]=0;
			force[2*K+2]=0;
			//Calculation all Forces
					for (int i = 0; i < 2*n+5; i++) {
						for (int j = 0; j < 2*n+5; j++) {
							force[i] += global[i][j]*displacement[j];
						}
					}
					
			//Printing all forces
					System.out.println("Forces :");
					for (int i = 0; i < 2*n+5; i++) {
						System.out.println("Force correspond to "+ (i+1) + " : "+ force[i] + "  Displacement : "+ displacement[i]);
					}System.out.println();
				
			//Storing vertical force
					System.out.println("Verical Forces :");
					float verticalforce[]=new float[n+2];
					int j=0;
					for (int i = 0; i < n+2; i++) {
						if (i<=K) {verticalforce[i]= force[2*j];}
						else 	 {verticalforce[i]= force[2*j+1];}
						j++;
					}
		
			//Printing Vertical Force
			for (int i = 0; i < n+2; i++) {
				System.out.println(verticalforce[i]);
			} System.out.println();
			
			//Finding Final Equation
			float valueofx[] = new float[n+2];
			valueofx[0]=0;
			for (int i = 0; i < n+1; i++) {
				for (int m = 0; m < i+1; m++) {
					valueofx[i+1]+=L[m];
					
					}System.out.println("Value of x: "+valueofx[i+1]);
			}
			
			float [][][] array1= new float[n+1][4][4];		
			
			for (int i = 0; i < n+1; i++) {
				array1[i][0][0] = valueofx[i]*valueofx[i]*valueofx[i];
				array1[i][0][1] = valueofx[i]*valueofx[i];
				array1[i][0][2] = valueofx[i];
				array1[i][0][3] = 1;
				array1[i][1][0] = 3*valueofx[i]*valueofx[i];
				array1[i][1][1] = 2*valueofx[i];
				array1[i][1][2] = 1;
				array1[i][1][3] = 0;
				array1[i][2][0] = valueofx[i+1]*valueofx[i+1]*valueofx[i+1];
				array1[i][2][1] = valueofx[i+1]*valueofx[i+1];
				array1[i][2][2] = valueofx[i+1];
				array1[i][2][3] = 1;
				array1[i][3][0] = 3*valueofx[i+1]*valueofx[i+1];
				array1[i][3][1] = 2*valueofx[i+1];
				array1[i][3][2] = 1;
				array1[i][3][3] = 0;	
			}

			float inv[][] = new float[n+4][n+4];
			float abcd [][] = new float[n+1][4];
			
			for (int i = 0; i < n+1; i++) {
				float [][] matrix = {{array1[i][0][0],array1[i][0][1],array1[i][0][2],array1[i][0][3]} , {array1[i][1][0],array1[i][1][1],array1[i][1][2],array1[i][1][3]} , {array1[i][2][0],array1[i][2][1],array1[i][2][2],array1[i][2][3]} , {array1[i][3][0],array1[i][3][1],array1[i][3][2],array1[i][3][3]}};
				inv = Inverse.invert(matrix);
				
					for (int r = 0; r < 4; r++) {
						for (int r2 = 0; r2 < 4; r2++) {
							System.out.print("Inverse : ");
							System.out.print(inv[r][r2]+"  ");
						} System.out.println();
					}System.out.println();
					
							for (int m2 = 0; m2 < 4; m2++) {
								int r=2*i;
								if (r>2*K) {r++;}
								for (int m3 = 0; m3 < 4; m3++) {
									abcd[i][m2]+= inv[m2][m3]*displacement[r];
									System.out.println("ABCD value for "+ i+"and "+r+" is :"+abcd[i][m2]);
									if (r==2*K&i==K) {r++;}
									r++;
									
								}
							}
		
			}
			
			System.out.println();
			float Scalefactor = displacement[2*K+1]-displacement[2*K+2];
			System.out.println("Values of Scalefactor: " + Scalefactor);  System.out.println(); 
			
			valueofy = new double[101*n+101] ;
			double X1;
			int r= 0;
			for (int i1 = 0; i1 < n+1; i1++) {
					 X1= valueofx[i1];
					while (X1<=valueofx[i1+1]) {					
							valueofy[r] = (X1*X1*X1*abcd[i1][0]+X1*X1*abcd[i1][1]+X1*abcd[i1][2]+abcd[i1][3])/Scalefactor;
							System.out.println("Values of x "+X1+" for y: " + valueofy[r]);	
							X1=X1+0.01*L[i1];
							r++;
					}
			}
				
		
			for (int e= 0; e < 101*n+101; e++) {
				ar[0][e] = valueofy[e];
				
			}
			
		
		}
		
		// Moment &	Both End Fixed
		if(shearormoment.length()==6 && endsupports.length()==26) {

			System.out.println("After all declinationn it is assummed it is both side Fixed Support");
	
			/*
			// number of beam input from user
			
			System.out.println("Enter number of beam : ");
			int n = sc.nextInt(); 
			    
			System.out.println("Enter the beam number on which point of interest lies is :  ");
			int K = sc.nextInt(); 
			
			L = new double[n+1]; 
		
			// Distance between supports 
			for (int i = 0; i <n+1 ; i++) {
				 
						if (i!=K-1 && i!=(K)) {
						if (i<K-1) {
							System.out.println("Enter Length of " + (i+1) + " beam : ");
							L[i] =  sc.nextFloat();} 
						else{
							System.out.println("Enter Length of " + (i) + " beam : ");
							L[i] =  sc.nextFloat(); }}
					
					
						if(i==K-1) {
							System.out.println("Enter distance of point of interest from left nearest support: ");
							L[i] = sc.nextInt();
						}
						if(i==K) {
							System.out.println("Enter distance of point of interest from right nearest support: ");
							L[i] = sc.nextInt(); }		
			}
			
			*/
			//Local Matrix
			double [][][]local = new double[n+1][4][4];
			for (int i = 0; i<n+1; i++) {
				local[i][0][0] = 12/(L[i]*L[i]*L[i]);
				local[i][0][1] = 6/((L[i])*(L[i]));
				local[i][0][2] = -12/(L[i]*L[i]*L[i]);
				local[i][0][3] = 6/(L[i]*L[i]);
				local[i][1][0] = 6/(L[i]*L[i]);
				local[i][1][1] = 4/(L[i]);
				local[i][1][2] = -6/(L[i]*L[i]);
				local[i][1][3] = 2/L[i];
				local[i][2][0] = -12/(L[i]*L[i]*L[i]);
				local[i][2][1] = -6/(L[i]*L[i]);
				local[i][2][2] = 12/(L[i]*L[i]*L[i]);
				local[i][2][3] = -6/(L[i]*L[i]);
				local[i][3][0] = 6/(L[i]*L[i]);
				local[i][3][1] = 2/(L[i]);
				local[i][3][2] = -6/(L[i]*L[i]);
				local[i][3][3] = 4/L[i];			
			}
			
			//Printing Local Matrix
			System.out.println();
			System.out.println("The Local Matrix");
			
				for (int i = 0; i < n+1; i++) {
					for (int j = 0; j < 4; j++) {
						for (int k = 0; k < 4; k++) {System.out.print(local[i][j][k] + "  ");} System.out.println();
					}System.out.println();
				}

		
			//Global Matrix
			float [][] global= new float[2*n+5][2*n+5];
			
				for (int i = 0; i < n+1; i++) {
					int k;
					int l;
						if (i<=K) {
							k = 2*i;
							l=2*i;
						}	
						else { 
							k = 2*i+1;
							l=2*i+1;
						}
								for (int m = 0; m < 4; m++) {
							
										for (int r = 0; r < 4; r++) {
												if (k==2*K+1&&i>=K) {
														k=k+1;
													}
													if (l==2*K+1&&i>=K) {
															l=l+1;
													}
											global[k][l] += local[i][m][r];
											l++;
										} 
											if (i<=K) {
													l=2*i;	
											}	
											else {
												l=2*i+1;
											}
									k++;
								}
				}
		
				
			//Printing Global Matrix
			System.out.println();
			System.out.println("The Global Matrix");
			for (int i = 0; i < 2*n+5; i++) {
				for (int j = 0; j < 2*n+5; j++) {
					System.out.print(global[i][j] + "    ");
				
				} System.out.println();
			}
		
			
			 
			// GlobalK1 Matrix
			float globalk1[][] = new float[n+2][n+2];
			int l=0;
			int q=0;
			for (int i = 0; i <n+2; i++) {
				
					if (i<K-1) {l=2*i+3;}
					if (i==K-1) {l=2*i+2;}
					if (i==K) {l=2*i+1;}
					if (i>=K+1) {l=2*i;}
					
			
					for (int j = 0; j < n+2; j++) {
						if (j<K-1) {q=2*j+3;}
						if (j==K-1) {q=2*j+2;}
						if (j==K) {q=2*j+1;}
						if (j>=K+1) {q=2*j;}
					
						globalk1[i][j] = global[l][q];
						
					}
			}

			// Printing GlobalK1 Matrix
			System.out.println();
			System.out.println("The GlobalK1 Matrix");
			for (int i = 0; i <n+2; i++) {
					for (int j = 0; j < n+2; j++) {
						System.out.print(globalk1[i][j] + "    ");
					} System.out.println();
			}
		
			// Inverse of GlobalK1 Matrix 
			float inverse[][] = new float[n+2][n+2];
			inverse = Inverse.invert(globalk1);
			System.out.println();
			System.out.println("Inverse of globalK1 Matrix");
			
			// Printing of Inverse of GlobalK1 Matrix 
			for (int i = 0; i < n+2; i++) {
				for (int j = 0; j < n+2; j++) {
					System.out.print(inverse[i][j]);
				} System.out.println();
			} System.out.println();
			
			// Defining Force Matrix
			float[] force= new float[2*n+5];
			force[2*K+1]=1;
			force[2*K+2]=-1;
			
			//Defining Displacement Matrix
			float[] displacement= new float[2*n+5];
	
			System.out.println("Displacements");
			for (int i = 0; i < n+2; i++) {
				
					for (int j = 0; j < n+2; j++) {
						if (i<K-1) {
							if (j<K-1) 	{displacement[2*i+3]+=inverse[i][j]*force[2*j+3];}
							if (j==K-1)	{displacement[2*i+3]+=inverse[i][j]*force[2*j+2];}
							if (j==K) {displacement[2*i+3]+=inverse[i][j]*force[2*j+1];}
							if (j>=K+1) {displacement[2*i+3]+=inverse[i][j]*force[2*j];}
						
						}
				
						if (i==K-1) {
							if (j<K-1)    {displacement[2*i+2]+=inverse[i][j]*force[2*j+3];}
							if (j==K-1)   {displacement[2*i+2]+=inverse[i][j]*force[2*+2];}
							if (j==K) {displacement[2*i+2]+=inverse[i][j]*force[2*j+1];}
							if (j>=K+1) {displacement[2*i+2]+=inverse[i][j]*force[2*j];}
						}
					
						if (i==K) {
							if (j<K-1)    {displacement[2*i+1]+=inverse[i][j]*force[2*j+3];}
							if (j==K-1)   {displacement[2*i+1]+=inverse[i][j]*force[2*j+2];}
							if (j==K) {displacement[2*i+1]+=inverse[i][j]*force[2*j+1];}
							if (j>=K+1) {displacement[2*i+1]+=inverse[i][j]*force[2*j];}
						}
					
						if (i>=K+1) {
							if (j<K-1)    {displacement[2*i]+=inverse[i][j]*force[2*j+3];}
							if (j==K-1)   {displacement[2*i]+=inverse[i][j]*force[2*j+2];}
							if (j==K) {displacement[2*i]+=inverse[i][j]*force[2*j+1];}
							if (j>=K+1) {displacement[2*i]+=inverse[i][j]*force[2*j];}					
						}
				
					}
			}
		
			//Printing Displacement
			for (int i = 0; i < 2*n+5; i++) {
			System.out.println("Displacement : "+i+" "+displacement[i]);	
			} System.out.println();
		
			force[2*K+1]=0;
			force[2*K+2]=0;
			//Calculation all Forces
					for (int i = 0; i < 2*n+5; i++) {
						for (int j = 0; j < 2*n+5; j++) {
							force[i] += global[i][j]*displacement[j];
						}
					}
					
			//Printing all forces
					System.out.println("Forces :");
					for (int i = 0; i < 2*n+5; i++) {
						System.out.println("Force correspond to "+ (i+1) + " : "+ force[i] + "  Displacement : "+ displacement[i]);
					}System.out.println();
				
			//Storing vertical force
					System.out.println("Verical Forces :");
					float verticalforce[]=new float[n+2];
					int j=0;
					for (int i = 0; i < n+2; i++) {
						if (i<=K) {verticalforce[i]= force[2*j];}
						else 	 {verticalforce[i]= force[2*j+1];}
						j++;
					}
		
			//Printing Vertical Force
			for (int i = 0; i < n+2; i++) {
				System.out.println(verticalforce[i]);
			} System.out.println();
			
			//Finding Final Equation
			float valueofx[] = new float[n+2];
			valueofx[0]=0;
			for (int i = 0; i < n+1; i++) {
				for (int m = 0; m < i+1; m++) {
					valueofx[i+1]+=L[m];
					
					}System.out.println("Value of x: "+valueofx[i+1]);
			}
			
			float [][][] array1= new float[n+1][4][4];		
			
			for (int i = 0; i < n+1; i++) {
				array1[i][0][0] = valueofx[i]*valueofx[i]*valueofx[i];
				array1[i][0][1] = valueofx[i]*valueofx[i];
				array1[i][0][2] = valueofx[i];
				array1[i][0][3] = 1;
				array1[i][1][0] = 3*valueofx[i]*valueofx[i];
				array1[i][1][1] = 2*valueofx[i];
				array1[i][1][2] = 1;
				array1[i][1][3] = 0;
				array1[i][2][0] = valueofx[i+1]*valueofx[i+1]*valueofx[i+1];
				array1[i][2][1] = valueofx[i+1]*valueofx[i+1];
				array1[i][2][2] = valueofx[i+1];
				array1[i][2][3] = 1;
				array1[i][3][0] = 3*valueofx[i+1]*valueofx[i+1];
				array1[i][3][1] = 2*valueofx[i+1];
				array1[i][3][2] = 1;
				array1[i][3][3] = 0;	
			}

			float inv[][] = new float[n+4][n+4];
			float abcd [][] = new float[n+1][4];
			
			for (int i = 0; i < n+1; i++) {
				float [][] matrix = {{array1[i][0][0],array1[i][0][1],array1[i][0][2],array1[i][0][3]} , {array1[i][1][0],array1[i][1][1],array1[i][1][2],array1[i][1][3]} , {array1[i][2][0],array1[i][2][1],array1[i][2][2],array1[i][2][3]} , {array1[i][3][0],array1[i][3][1],array1[i][3][2],array1[i][3][3]}};
				inv = Inverse.invert(matrix);
				
					for (int r = 0; r < 4; r++) {
						for (int r2 = 0; r2 < 4; r2++) {
							System.out.print("Inverse : ");
							System.out.print(inv[r][r2]+"  ");
						} System.out.println();
					}System.out.println();
					
							for (int m2 = 0; m2 < 4; m2++) {
								int r=2*i;
								if (r>2*K) {r++;}
								for (int m3 = 0; m3 < 4; m3++) {
									abcd[i][m2]+= inv[m2][m3]*displacement[r];
									System.out.println("ABCD value for "+ i+"and "+r+" is :"+abcd[i][m2]);
									if (r==2*K&i==K) {r++;}
									r++;
									
								}
							}
		
			}
			
			System.out.println();
			float Scalefactor = displacement[2*K+1]-displacement[2*K+2];
			System.out.println("Values of Scalefactor: " + Scalefactor);  System.out.println(); 
			
			valueofy = new double[101*n+101] ;
			double X1;
			int r= 0;
			for (int i1 = 0; i1 < n+1; i1++) {
					 X1= valueofx[i1];
					while (X1<=valueofx[i1+1]) {					
							valueofy[r] = (X1*X1*X1*abcd[i1][0]+X1*X1*abcd[i1][1]+X1*abcd[i1][2]+abcd[i1][3])/Scalefactor;
							System.out.println("Values of x "+X1+" for y: " + valueofy[r]);	
							X1=X1+0.01*L[i1];
							r++;
					}
				
			}
		
			for (int e= 0; e < 101*n+101; e++) {
				ar[0][e] = valueofy[e];
				
			}
		
		
		
		}
		
		//Shear & Pinned
		if(shearormoment.length()==5 && endsupports.length()==18) {	

			System.out.println("Pinned Support");
	
			/*
			// number of beam input from user
			 
			System.out.println("Enter number of beam : ");
			int n = sc.nextInt(); 
			    
			System.out.println("Enter the beam number on which point of interest lies is :  ");
			int K = sc.nextInt(); 
			
			 L = new double[n+1]; 
		
			// Distance between supports 
			for (int i = 0; i <n+1 ; i++) {
				 
						if (i!=K-1 && i!=(K)) {
						if (i<K-1) {
							System.out.println("Enter Length of " + (i+1) + " beam : ");
							L[i] =  sc.nextFloat();} 
						else{
							System.out.println("Enter Length of " + (i) + " beam : ");
							L[i] =  sc.nextFloat(); }}
					
					
						if(i==K-1) {
							System.out.println("Enter distance of point of interest from left nearest support: ");
							L[i] = sc.nextInt();
						}
						if(i==K) {
							System.out.println("Enter distance of point of interest from right nearest support: ");
							L[i] = sc.nextInt(); }		
			}
			
			*/
			
			//Local Matrix
			double [][][]local = new double [n+1][4][4];
			for (int i = 0; i<n+1; i++) {
				local[i][0][0] = 12/(L[i]*L[i]*L[i]);
				local[i][0][1] = 6/((L[i])*(L[i]));
				local[i][0][2] = -12/(L[i]*L[i]*L[i]);
				local[i][0][3] = 6/(L[i]*L[i]);
				local[i][1][0] = 6/(L[i]*L[i]);
				local[i][1][1] = 4/(L[i]);
				local[i][1][2] = -6/(L[i]*L[i]);
				local[i][1][3] = 2/L[i];
				local[i][2][0] = -12/(L[i]*L[i]*L[i]);
				local[i][2][1] = -6/(L[i]*L[i]);
				local[i][2][2] = 12/(L[i]*L[i]*L[i]);
				local[i][2][3] = -6/(L[i]*L[i]);
				local[i][3][0] = 6/(L[i]*L[i]);
				local[i][3][1] = 2/(L[i]);
				local[i][3][2] = -6/(L[i]*L[i]);
				local[i][3][3] = 4/L[i];
				
				if (i==K) {
					local[i][1][1] = 12/(L[i]*L[i]*L[i]);
					local[i][1][0] = 6/((L[i])*(L[i]));
					local[i][1][2] = -12/(L[i]*L[i]*L[i]);
					local[i][1][3] = 6/(L[i]*L[i]);
					local[i][0][1] = 6/(L[i]*L[i]);
					local[i][0][0] = 4/(L[i]);
					local[i][0][2] = -6/(L[i]*L[i]);
					local[i][0][3] = 2/L[i];
					local[i][2][1] = -12/(L[i]*L[i]*L[i]);
					local[i][2][0] = -6/(L[i]*L[i]);
					local[i][2][2] = 12/(L[i]*L[i]*L[i]);
					local[i][2][3] = -6/(L[i]*L[i]);
					local[i][3][1] = 6/(L[i]*L[i]);
					local[i][3][0] = 2/(L[i]);
					local[i][3][2] = -6/(L[i]*L[i]);
					local[i][3][3] = 4/L[i];
				}
				
			}
		
			//Printing Local Matrix
			System.out.println();
			System.out.println("The Local Matrix");
			
				for (int i = 0; i < n+1; i++) {
					for (int j = 0; j < 4; j++) {
						for (int k = 0; k < 4; k++) {System.out.print(local[i][j][k] + "  ");} System.out.println();
					}System.out.println();
				}

		
			//Global Matrix
			float [][] global= new float[2*n+5][2*n+5];
			
				for (int i = 0; i < n+1; i++) {
					int k;
					int l;
					if (i<K) {
					 k = 2*i;
					 l = 2*i;
					}	
					else {
					 k = 2*i+1;
					 l = 2*i+1;
					}
				
						for (int m = 0; m < 4; m++) {
							
							
								for (int r = 0; r < 4; r++) {
									global[k][l] += local[i][m][r];	
									l++;
								} 
											if (i<K) {l=2*i;}	
											else {l=2*i+1;}
							k++;
						}
			
				}
		
				
			//Printing Global Matrix
			System.out.println();
			System.out.println("The Global Matrix");
			for (int i = 0; i < 2*n+5; i++) {
				for (int j = 0; j < 2*n+5; j++) {
					System.out.print(global[i][j] + "    ");
				
				} System.out.println();
			}
		
				 
			// GlobalK1 Matrix
			float globalk1[][] = new float[n+4][n+4];
			int l=0;
			int q=0;
			for (int i = 0; i <n+4; i++) {
				
					if (i<K) {l=2*i+1;}
					if (i==K) {l=2*i;}
					if (i==K+1) {l=2*i-1;}
					if (i>=K+2) {l=2*i-2;}
				
			
					for (int j = 0; j < n+4; j++) {
						if (j<K) {q=2*j+1;}
						if (j==K) {q=2*j;}
						if (j==K+1) {q=2*j-1;}
						if (j>=K+2) {q=2*j-2;}
						
						globalk1[i][j] = global[l][q];
					
					}
				
			}

			// Printing GlobalK1 Matrix
			System.out.println();
			System.out.println("The GlobalK1 Matrix");
			for (int i = 0; i <n+4; i++) {
					for (int j = 0; j < n+4; j++) {
						System.out.print(globalk1[i][j] + "    ");
					} System.out.println();
			}
		

				
			// Inverse of GlobalK1 Matrix 
			float inverse[][] = new float[n+4][n+4];
			inverse = Inverse.invert(globalk1);
			System.out.println();
			System.out.println("Inverse of globalK1 Matrix");
			
			// Printing of Inverse of GlobalK1 Matrix 
			for (int i = 0; i < n+4; i++) {
				for (int j = 0; j < n+4; j++) {
					System.out.print(inverse[i][j]);
				} System.out.println();
			} System.out.println();
			
			// Defining Force Matrix
			float[] force= new float[2*n+5];
			force[2*K]=1;
			force[2*K+2]=-1;
			
			//Defining Displacement Matrix
			float[] displacement= new float[2*n+5];

			System.out.println("Displacements");
			for (int i = 0; i < n+4; i++) {
				
					for (int j = 0; j < n+4; j++) {
						if (i<K) {
							if (j<K) 	{displacement[2*i+1]+=inverse[i][j]*force[2*j+1];}
							if (j==K)	{displacement[2*i+1]+=inverse[i][j]*force[2*j];}
							if (j==K+1) {displacement[2*i+1]+=inverse[i][j]*force[2*j-1];}
							if (j>=K+2) {displacement[2*i+1]+=inverse[i][j]*force[2*j-2];}
						
						}
				
						if (i==K) {
							if (j<K)    {displacement[2*i]+=inverse[i][j]*force[2*j+1];}
							if (j==K)   {displacement[2*i]+=inverse[i][j]*force[2*j];}
							if (j==K+1) {displacement[2*i]+=inverse[i][j]*force[2*j-1];}
							if (j>=K+2) {displacement[2*i]+=inverse[i][j]*force[2*j-2];}
						}
					
						if (i==K+1) {
							if (j<K)    {displacement[2*i-1]+=inverse[i][j]*force[2*j+1];}
							if (j==K)   {displacement[2*i-1]+=inverse[i][j]*force[2*j];}
							if (j==K+1) {displacement[2*i-1]+=inverse[i][j]*force[2*j-1];}
							if (j>=K+2) {displacement[2*i-1]+=inverse[i][j]*force[2*j-2];}
						}
					
						if (i>=K+2) {
							if (j<K)    {displacement[2*i-2]+=inverse[i][j]*force[2*j+1];}
							if (j==K)   {displacement[2*i-2]+=inverse[i][j]*force[2*j];}
							if (j==K+1) {displacement[2*i-2]+=inverse[i][j]*force[2*j-1];}
							if (j>=K+2) {displacement[2*i-2]+=inverse[i][j]*force[2*j-2];}					
						}
				 
					}
			}

			//Printing Displacement
			for (int i = 0; i < 2*n+5; i++) {
			System.out.println("Displacement : "+i+" "+displacement[i]);	
			} System.out.println();
			
			force[2*K]=0;
			force[2*K+2]=0;
			//Calculation all Forces
					for (int i = 0; i < 2*n+5; i++) {
						for (int j = 0; j < 2*n+5; j++) {
							force[i] += global[i][j]*displacement[j];
						}
					}
					
			//Printing all forces
					System.out.println("Forces :");
					for (int i = 0; i < 2*n+5; i++) {
						System.out.println("Force correspond to "+ (i+1) + " : "+ force[i] + "  Displacement : "+ displacement[i]);
					}System.out.println();
					
			//Storing vertical force
					System.out.println("Verical Forces :");
					float verticalforce[]=new float[n+2];
					int j=0;
					for (int i = 0; i < n+2; i++) {
						if (i<=K) {verticalforce[i]= force[2*j];}
						else 	 {verticalforce[i]= force[2*j+1];}
						j++;
					}
		
			//Printing Vertical Force
			for (int i = 0; i < n+2; i++) {
				System.out.println(verticalforce[i]);
			} System.out.println();
		
		
	//Finding Final Equation
			
			//Value of x
			float valueofx[] = new float[n+2];
			valueofx[0]=0;
			for (int i = 0; i < n+1; i++) {
				for (int m = 0; m < i+1; m++) {
					valueofx[i+1]+=L[m];
					
					}System.out.println("Value of x: "+valueofx[i+1]);
			}
			
			float [][][] array1= new float[n+1][4][4];		
			
			for (int i = 0; i < n+1; i++) {
				array1[i][0][0] = valueofx[i]*valueofx[i]*valueofx[i];
				array1[i][0][1] = valueofx[i]*valueofx[i];
				array1[i][0][2] = valueofx[i];
				array1[i][0][3] = 1;
				array1[i][1][0] = 3*valueofx[i]*valueofx[i];
				array1[i][1][1] = 2*valueofx[i];
				array1[i][1][2] = 1;
				array1[i][1][3] = 0;
				array1[i][2][0] = valueofx[i+1]*valueofx[i+1]*valueofx[i+1];
				array1[i][2][1] = valueofx[i+1]*valueofx[i+1];
				array1[i][2][2] = valueofx[i+1];
				array1[i][2][3] = 1;
				array1[i][3][0] = 3*valueofx[i+1]*valueofx[i+1];
				array1[i][3][1] = 2*valueofx[i+1];
				array1[i][3][2] = 1;
				array1[i][3][3] = 0;	
			}

			float inv[][] = new float[n+4][n+4];
			float abcd [][] = new float[n+1][4];

			for (int i = 0; i < n+1; i++) {
				float [][] matrix = {{array1[i][0][0],array1[i][0][1],array1[i][0][2],array1[i][0][3]} , {array1[i][1][0],array1[i][1][1],array1[i][1][2],array1[i][1][3]} , {array1[i][2][0],array1[i][2][1],array1[i][2][2],array1[i][2][3]} , {array1[i][3][0],array1[i][3][1],array1[i][3][2],array1[i][3][3]}};
				inv = Inverse.invert(matrix);
				
					for (int r = 0; r < 4; r++) {
						for (int r2 = 0; r2 < 4; r2++) {
							System.out.print("Inverse : ");
							System.out.print(inv[r][r2]+"  ");
						} System.out.println();
					}System.out.println();
					
							int r=0;
							for (int m2 = 0; m2 < 4; m2++) {
								
								if (i<K) {r=2*i;}
								else {r=2*i+1;}
								
							
								for (int m3 = 0; m3 < 4; m3++) {
									if (i==K&&m3==0) {r=2*K+2;}
									if (i==K&&m3==1) {r=2*K+1;}
									if (i==K&&m3==2) {r=2*K+3;}
									abcd[i][m2]+= inv[m2][m3]*displacement[r];
									System.out.println("ABCD value for "+ i+"and "+r+" is :"+abcd[i][m2]);
									
									r++;
								}
							}
		
			}
			
			
			System.out.println();
			float Scalefactor = displacement[2*K]-displacement[2*K+2];
			System.out.println("Values of Scalefactor: " + Scalefactor);  System.out.println(); 
			
			valueofy = new double[101*n+101] ;
			double X1;
			int r=0;
			
			for (int i1 = 0; i1 < n+1; i1++) {
					 X1= valueofx[i1];
					 
					 while (X1<=valueofx[i1+1]) {					
							valueofy[r] = (X1*X1*X1*abcd[i1][0]+X1*X1*abcd[i1][1]+X1*abcd[i1][2]+abcd[i1][3])/Scalefactor;
							System.out.println("Values of x "+X1+" for y: " + valueofy[r]);	
							X1=X1+0.01*L[i1];
							r++;
							
					}
				
			}
			
			for (int e= 0; e < 101*n+101; e++) {
				ar[0][e] = valueofy[e];
			
			}
			
		
		
		}
	
		//Shear & One End Fixed
		if(shearormoment.length()==5 && endsupports.length()==22) {	

			System.out.println("one end Fixed Support");
			
			/*
			// number of beam input from user
			
			System.out.println("Enter number of beam : ");
			int n = sc.nextInt(); 
			    
			System.out.println("Enter the beam number on which point of interest lies is :  ");
			int K = sc.nextInt(); 
			
			 L = new double[n+1]; 
		
			// Distance between supports 
			for (int i = 0; i <n+1 ; i++) {
				 
						if (i!=K-1 && i!=(K)) {
						if (i<K-1) {
							System.out.println("Enter Length of " + (i+1) + " beam : ");
							L[i] =  sc.nextFloat();} 
						else{
							System.out.println("Enter Length of " + (i) + " beam : ");
							L[i] =  sc.nextFloat(); }}
					
					
						if(i==K-1) {
							System.out.println("Enter distance of point of interest from left nearest support: ");
							L[i] = sc.nextInt();
						}
						if(i==K) {
							System.out.println("Enter distance of point of interest from right nearest support: ");
							L[i] = sc.nextInt(); }		
			}
			*/
			
			//Local Matrix
			double [][][]local = new double[n+1][4][4];
			for (int i = 0; i<n+1; i++) {
				local[i][0][0] = 12/(L[i]*L[i]*L[i]);
				local[i][0][1] = 6/((L[i])*(L[i]));
				local[i][0][2] = -12/(L[i]*L[i]*L[i]);
				local[i][0][3] = 6/(L[i]*L[i]);
				local[i][1][0] = 6/(L[i]*L[i]);
				local[i][1][1] = 4/(L[i]);
				local[i][1][2] = -6/(L[i]*L[i]);
				local[i][1][3] = 2/L[i];
				local[i][2][0] = -12/(L[i]*L[i]*L[i]);
				local[i][2][1] = -6/(L[i]*L[i]);
				local[i][2][2] = 12/(L[i]*L[i]*L[i]);
				local[i][2][3] = -6/(L[i]*L[i]);
				local[i][3][0] = 6/(L[i]*L[i]);
				local[i][3][1] = 2/(L[i]);
				local[i][3][2] = -6/(L[i]*L[i]);
				local[i][3][3] = 4/L[i];
				
				if (i==K) {
					local[i][1][1] = 12/(L[i]*L[i]*L[i]);
					local[i][1][0] = 6/((L[i])*(L[i]));
					local[i][1][2] = -12/(L[i]*L[i]*L[i]);
					local[i][1][3] = 6/(L[i]*L[i]);
					local[i][0][1] = 6/(L[i]*L[i]);
					local[i][0][0] = 4/(L[i]);
					local[i][0][2] = -6/(L[i]*L[i]);
					local[i][0][3] = 2/L[i];
					local[i][2][1] = -12/(L[i]*L[i]*L[i]);
					local[i][2][0] = -6/(L[i]*L[i]);
					local[i][2][2] = 12/(L[i]*L[i]*L[i]);
					local[i][2][3] = -6/(L[i]*L[i]);
					local[i][3][1] = 6/(L[i]*L[i]);
					local[i][3][0] = 2/(L[i]);
					local[i][3][2] = -6/(L[i]*L[i]);
					local[i][3][3] = 4/L[i];
				}
				
			}
		
			//Printing Local Matrix
			System.out.println();
			System.out.println("The Local Matrix");
			
				for (int i = 0; i < n+1; i++) {
					for (int j = 0; j < 4; j++) {
						for (int k = 0; k < 4; k++) {System.out.print(local[i][j][k] + "  ");} System.out.println();
					}System.out.println();
				}

		
			//Global Matrix
			float [][] global= new float[2*n+5][2*n+5];
			
				for (int i = 0; i < n+1; i++) {
					int k;
					int l;
					if (i<K) {
					 k = 2*i;
					 l = 2*i;
					}	
					else {
					 k = 2*i+1;
					 l = 2*i+1;
					}
				
						for (int m = 0; m < 4; m++) {
							
							
								for (int r = 0; r < 4; r++) {
									global[k][l] += local[i][m][r];	
									l++;
								} 
											if (i<K) {l=2*i;}	
											else {l=2*i+1;}
							k++;
						}
			
				}
		
				
			//Printing Global Matrix
			System.out.println();
			System.out.println("The Global Matrix");
			for (int i = 0; i < 2*n+5; i++) {
				for (int j = 0; j < 2*n+5; j++) {
					System.out.print(global[i][j] + "    ");
				
				} System.out.println();
			}
		
				 
			// GlobalK1 Matrix
			float globalk1[][] = new float[n+3][n+3];
			int l=0;
			int q=0;
			for (int i = 0; i <n+3; i++) {
				
				if (i<K-1) {l=2*i+3;}
				if (i==K-1) {l=2*i+2;}
				if (i==K) {l=2*i+1;}
				if (i>=K+1) {l=2*i;}
				
		
				for (int j = 0; j < n+3; j++) {
					if (j<K-1) {q=2*j+3;}
					if (j==K-1) {q=2*j+2;}
					if (j==K) {q=2*j+1;}
					if (j>=K+1) {q=2*j;}
						
						globalk1[i][j] = global[l][q];
					
					}
				
			}

			// Printing GlobalK1 Matrix
			System.out.println();
			System.out.println("The GlobalK1 Matrix");
			for (int i = 0; i <n+3; i++) {
					for (int j = 0; j < n+3; j++) {
						System.out.print(globalk1[i][j] + "    ");
					} System.out.println();
			}
		

				
			// Inverse of GlobalK1 Matrix 
			float inverse[][] = new float[n+3][n+3];
			inverse = Inverse.invert(globalk1);
			System.out.println();
			System.out.println("Inverse of globalK1 Matrix");
			
			// Printing of Inverse of GlobalK1 Matrix 
			for (int i = 0; i < n+3; i++) {
				for (int j = 0; j < n+3; j++) {
					System.out.print(inverse[i][j]);
				} System.out.println();
			} System.out.println();
			
			// Defining Force Matrix
			float[] force= new float[2*n+5];
			force[2*K]=1;
			force[2*K+2]=-1;
			
			//Defining Displacement Matrix
			float[] displacement= new float[2*n+5];

			System.out.println("Displacements");
			for (int i = 0; i < n+3; i++) {
				
				for (int j = 0; j < n+3; j++) {
					if (i<K-1) {
						if (j<K-1) 	{displacement[2*i+3]+=inverse[i][j]*force[2*j+3];}
						if (j==K-1)	{displacement[2*i+3]+=inverse[i][j]*force[2*j+2];}
						if (j==K) {displacement[2*i+3]+=inverse[i][j]*force[2*j+1];}
						if (j>=K+1) {displacement[2*i+3]+=inverse[i][j]*force[2*j];}
					
					}
			
					if (i==K-1) {
						if (j<K-1)    {displacement[2*i+2]+=inverse[i][j]*force[2*j+3];}
						if (j==K-1)   {displacement[2*i+2]+=inverse[i][j]*force[2*j+2];}
						if (j==K) {displacement[2*i+2]+=inverse[i][j]*force[2*j+1];}
						if (j>=K+1) {displacement[2*i+2]+=inverse[i][j]*force[2*j];}
					}
				
					if (i==K) {
						if (j<K-1)    {displacement[2*i+1]+=inverse[i][j]*force[2*j+3];}
						if (j==K-1)   {displacement[2*i+1]+=inverse[i][j]*force[2*j+2];}
						if (j==K) {displacement[2*i+1]+=inverse[i][j]*force[2*j+1];}
						if (j>=K+1) {displacement[2*i+1]+=inverse[i][j]*force[2*j];}
					}
				
					if (i>=K+1) {
						if (j<K-1)    {displacement[2*i]+=inverse[i][j]*force[2*j+3];}
						if (j==K-1)   {displacement[2*i]+=inverse[i][j]*force[2*j+2];}
						if (j==K) {displacement[2*i]+=inverse[i][j]*force[2*j+1];}
						if (j>=K+1) {displacement[2*i]+=inverse[i][j]*force[2*j];}					
					}
			 
				}
		}

			//Printing Displacement
			for (int i = 0; i < 2*n+5; i++) {
			System.out.println("Displacement : "+i+" "+displacement[i]);	
			} System.out.println();
			
			force[2*K]=0;
			force[2*K+2]=0;
			//Calculation all Forces
					for (int i = 0; i < 2*n+5; i++) {
						for (int j = 0; j < 2*n+5; j++) {
							force[i] += global[i][j]*displacement[j];
						}
					}
					
			//Printing all forces
					System.out.println("Forces :");
					for (int i = 0; i < 2*n+5; i++) {
						System.out.println("Force correspond to "+ (i+1) + " : "+ force[i] + "  Displacement : "+ displacement[i]);
					}System.out.println();
					
			//Storing vertical force
					System.out.println("Verical Forces :");
					float verticalforce[]=new float[n+2];
					int j=0;
					for (int i = 0; i < n+2; i++) {
						if (i<=K) {verticalforce[i]= force[2*j];}
						else 	 {verticalforce[i]= force[2*j+1];}
						j++;
					}
		
			//Printing Vertical Force
			for (int i = 0; i < n+2; i++) {
				System.out.println(verticalforce[i]);
			} System.out.println();
		
		
	//Finding Final Equation
			
			//Value of x
			float valueofx[] = new float[n+2];
			valueofx[0]=0;
			for (int i = 0; i < n+1; i++) {
				for (int m = 0; m < i+1; m++) {
					valueofx[i+1]+=L[m];
					
					}System.out.println("Value of x: "+valueofx[i+1]);
			}
			
			float [][][] array1= new float[n+1][4][4];		
			
			for (int i = 0; i < n+1; i++) {
				array1[i][0][0] = valueofx[i]*valueofx[i]*valueofx[i];
				array1[i][0][1] = valueofx[i]*valueofx[i];
				array1[i][0][2] = valueofx[i];
				array1[i][0][3] = 1;
				array1[i][1][0] = 3*valueofx[i]*valueofx[i];
				array1[i][1][1] = 2*valueofx[i];
				array1[i][1][2] = 1;
				array1[i][1][3] = 0;
				array1[i][2][0] = valueofx[i+1]*valueofx[i+1]*valueofx[i+1];
				array1[i][2][1] = valueofx[i+1]*valueofx[i+1];
				array1[i][2][2] = valueofx[i+1];
				array1[i][2][3] = 1;
				array1[i][3][0] = 3*valueofx[i+1]*valueofx[i+1];
				array1[i][3][1] = 2*valueofx[i+1];
				array1[i][3][2] = 1;
				array1[i][3][3] = 0;	
			}

			float inv[][] = new float[n+4][n+4];
			float abcd [][] = new float[n+1][4];

			for (int i = 0; i < n+1; i++) {
				float [][] matrix = {{array1[i][0][0],array1[i][0][1],array1[i][0][2],array1[i][0][3]} , {array1[i][1][0],array1[i][1][1],array1[i][1][2],array1[i][1][3]} , {array1[i][2][0],array1[i][2][1],array1[i][2][2],array1[i][2][3]} , {array1[i][3][0],array1[i][3][1],array1[i][3][2],array1[i][3][3]}};
				inv = Inverse.invert(matrix);
				
					for (int r = 0; r < 4; r++) {
						for (int r2 = 0; r2 < 4; r2++) {
							System.out.print("Inverse : ");
							System.out.print(inv[r][r2]+"  ");
						} System.out.println();
					}System.out.println();
					
							int r=0;
							for (int m2 = 0; m2 < 4; m2++) {
								
								if (i<K) {r=2*i;}
								else {r=2*i+1;}
								
							
								for (int m3 = 0; m3 < 4; m3++) {
									if (i==K&&m3==0) {r=2*K+2;}
									if (i==K&&m3==1) {r=2*K+1;}
									if (i==K&&m3==2) {r=2*K+3;}
									abcd[i][m2]+= inv[m2][m3]*displacement[r];
									System.out.println("ABCD value for "+ i+"and "+r+" is :"+abcd[i][m2]);
									
									r++;
								}
							}
		
			}
			
			
			System.out.println();
			float Scalefactor = displacement[2*K]-displacement[2*K+2];
			System.out.println("Values of Scalefactor: " + Scalefactor);  System.out.println(); 
			
			valueofy = new double[101*n+101] ;
			double X1;
			int r=0;
			
			for (int i1 = 0; i1 < n+1; i1++) {
					 X1= valueofx[i1];
					 
					 while (X1<=valueofx[i1+1]) {					
							valueofy[r] = (X1*X1*X1*abcd[i1][0]+X1*X1*abcd[i1][1]+X1*abcd[i1][2]+abcd[i1][3])/Scalefactor;
							System.out.println("Values of x "+X1+" for y: " + valueofy[r]);	
							X1=X1+0.01*L[i1];
							r++;
							
					}
				
			}
				
		
			for (int e= 0; e < 101*n+101; e++) {
				ar[0][e] = valueofy[e];
			
			}
			
		
		}
	
		//Shear & Both End Fixed
		if(shearormoment.length()==5 && endsupports.length()==26) {	

			System.out.println("After all declinationn it is assummed it is both side Fixed Support");
	
			/*
			// number of beam input from user
			
			System.out.println("Enter number of beam : ");
			int n = sc.nextInt(); 
			    
			System.out.println("Enter the beam number on which point of interest lies is :  ");
			int K = sc.nextInt(); 
			
			L = new double[n+1]; 
		
			// Distance between supports 
			for (int i = 0; i <n+1 ; i++) {
				 
						if (i!=K-1 && i!=(K)) {
						if (i<K-1) {
							System.out.println("Enter Length of " + (i+1) + " beam : ");
							L[i] =  sc.nextFloat();} 
						else{
							System.out.println("Enter Length of " + (i) + " beam : ");
							L[i] =  sc.nextFloat(); }}
					
					
						if(i==K-1) {
							System.out.println("Enter distance of point of interest from left nearest support: ");
							L[i] = sc.nextInt();
						}
						if(i==K) {
							System.out.println("Enter distance of point of interest from right nearest support: ");
							L[i] = sc.nextInt(); }		
			}
			
			*/
			//Local Matrix
			double [][][]local = new double[n+1][4][4];
			for (int i = 0; i<n+1; i++) {
				local[i][0][0] = 12/(L[i]*L[i]*L[i]);
				local[i][0][1] = 6/((L[i])*(L[i]));
				local[i][0][2] = -12/(L[i]*L[i]*L[i]);
				local[i][0][3] = 6/(L[i]*L[i]);
				local[i][1][0] = 6/(L[i]*L[i]);
				local[i][1][1] = 4/(L[i]);
				local[i][1][2] = -6/(L[i]*L[i]);
				local[i][1][3] = 2/L[i];
				local[i][2][0] = -12/(L[i]*L[i]*L[i]);
				local[i][2][1] = -6/(L[i]*L[i]);
				local[i][2][2] = 12/(L[i]*L[i]*L[i]);
				local[i][2][3] = -6/(L[i]*L[i]);
				local[i][3][0] = 6/(L[i]*L[i]);
				local[i][3][1] = 2/(L[i]);
				local[i][3][2] = -6/(L[i]*L[i]);
				local[i][3][3] = 4/L[i];
				
				if (i==K) {
					local[i][1][1] = 12/(L[i]*L[i]*L[i]);
					local[i][1][0] = 6/((L[i])*(L[i]));
					local[i][1][2] = -12/(L[i]*L[i]*L[i]);
					local[i][1][3] = 6/(L[i]*L[i]);
					local[i][0][1] = 6/(L[i]*L[i]);
					local[i][0][0] = 4/(L[i]);
					local[i][0][2] = -6/(L[i]*L[i]);
					local[i][0][3] = 2/L[i];
					local[i][2][1] = -12/(L[i]*L[i]*L[i]);
					local[i][2][0] = -6/(L[i]*L[i]);
					local[i][2][2] = 12/(L[i]*L[i]*L[i]);
					local[i][2][3] = -6/(L[i]*L[i]);
					local[i][3][1] = 6/(L[i]*L[i]);
					local[i][3][0] = 2/(L[i]);
					local[i][3][2] = -6/(L[i]*L[i]);
					local[i][3][3] = 4/L[i];
				}
				
			}
		
			//Printing Local Matrix
			System.out.println();
			System.out.println("The Local Matrix");
			
				for (int i = 0; i < n+1; i++) {
					for (int j = 0; j < 4; j++) {
						for (int k = 0; k < 4; k++) {System.out.print(local[i][j][k] + "  ");} System.out.println();
					}System.out.println();
				}

		
			//Global Matrix
			float [][] global= new float[2*n+5][2*n+5];
			
				for (int i = 0; i < n+1; i++) {
					int k;
					int l;
					if (i<K) {
					 k = 2*i;
					 l = 2*i;
					}	
					else {
					 k = 2*i+1;
					 l = 2*i+1;
					}
				
						for (int m = 0; m < 4; m++) {
							
							
								for (int r = 0; r < 4; r++) {
									global[k][l] += local[i][m][r];	
									l++;
								} 
											if (i<K) {l=2*i;}	
											else {l=2*i+1;}
							k++;
						}
			
				}
		
				
			//Printing Global Matrix
			System.out.println();
			System.out.println("The Global Matrix");
			for (int i = 0; i < 2*n+5; i++) {
				for (int j = 0; j < 2*n+5; j++) {
					System.out.print(global[i][j] + "    ");
				
				} System.out.println();
			}
		
				 
			// GlobalK1 Matrix
			float globalk1[][] = new float[n+2][n+2];
			int l=0;
			int q=0;
			for (int i = 0; i <n+2; i++) {
				
				if (i<K-1) {l=2*i+3;}
				if (i==K-1) {l=2*i+2;}
				if (i==K) {l=2*i+1;}
				if (i>=K+1) {l=2*i;}
				
		
				for (int j = 0; j < n+2; j++) {
					if (j<K-1) {q=2*j+3;}
					if (j==K-1) {q=2*j+2;}
					if (j==K) {q=2*j+1;}
					if (j>=K+1) {q=2*j;}
						
						globalk1[i][j] = global[l][q];
					
					}
				
			}

			// Printing GlobalK1 Matrix
			System.out.println();
			System.out.println("The GlobalK1 Matrix");
			for (int i = 0; i <n+2; i++) {
					for (int j = 0; j < n+2; j++) {
						System.out.print(globalk1[i][j] + "    ");
					} System.out.println();
			}
		

				
			// Inverse of GlobalK1 Matrix 
			float inverse[][] = new float[n+2][n+2];
			inverse = Inverse.invert(globalk1);
			System.out.println();
			System.out.println("Inverse of globalK1 Matrix");
			
			// Printing of Inverse of GlobalK1 Matrix 
			for (int i = 0; i < n+2; i++) {
				for (int j = 0; j < n+2; j++) {
					System.out.print(inverse[i][j]);
				} System.out.println();
			} System.out.println();
			
			// Defining Force Matrix
			float[] force= new float[2*n+5];
			force[2*K]=1;
			force[2*K+2]=-1;
			
			//Defining Displacement Matrix
			float[] displacement= new float[2*n+5];

			System.out.println("Displacements");
			for (int i = 0; i < n+2; i++) {
				
				for (int j = 0; j < n+2; j++) {
					if (i<K-1) {
						if (j<K-1) 	{displacement[2*i+3]+=inverse[i][j]*force[2*j+3];}
						if (j==K-1)	{displacement[2*i+3]+=inverse[i][j]*force[2*j+2];}
						if (j==K) {displacement[2*i+3]+=inverse[i][j]*force[2*j+1];}
						if (j>=K+1) {displacement[2*i+3]+=inverse[i][j]*force[2*j];}
					
					}
			
					if (i==K-1) {
						if (j<K-1)    {displacement[2*i+2]+=inverse[i][j]*force[2*j+3];}
						if (j==K-1)   {displacement[2*i+2]+=inverse[i][j]*force[2*j+2];}
						if (j==K) {displacement[2*i+2]+=inverse[i][j]*force[2*j+1];}
						if (j>=K+1) {displacement[2*i+2]+=inverse[i][j]*force[2*j];}
					}
				
					if (i==K) {
						if (j<K-1)    {displacement[2*i+1]+=inverse[i][j]*force[2*j+3];}
						if (j==K-1)   {displacement[2*i+1]+=inverse[i][j]*force[2*j+2];}
						if (j==K) {displacement[2*i+1]+=inverse[i][j]*force[2*j+1];}
						if (j>=K+1) {displacement[2*i+1]+=inverse[i][j]*force[2*j];}
					}
				
					if (i>=K+1) {
						if (j<K-1)    {displacement[2*i]+=inverse[i][j]*force[2*j+3];}
						if (j==K-1)   {displacement[2*i]+=inverse[i][j]*force[2*j+2];}
						if (j==K) {displacement[2*i]+=inverse[i][j]*force[2*j+1];}
						if (j>=K+1) {displacement[2*i]+=inverse[i][j]*force[2*j];}					
					}
			 
				}
		}

			//Printing Displacement
			for (int i = 0; i < 2*n+5; i++) {
			System.out.println("Displacement : "+i+" "+displacement[i]);	
			} System.out.println();
			
			force[2*K]=0;
			force[2*K+2]=0;
			//Calculation all Forces
					for (int i = 0; i < 2*n+5; i++) {
						for (int j = 0; j < 2*n+5; j++) {
							force[i] += global[i][j]*displacement[j];
						}
					}
					
			//Printing all forces
					System.out.println("Forces :");
					for (int i = 0; i < 2*n+5; i++) {
						System.out.println("Force correspond to "+ (i+1) + " : "+ force[i] + "  Displacement : "+ displacement[i]);
					}System.out.println();
					
			//Storing vertical force
					System.out.println("Verical Forces :");
					float verticalforce[]=new float[n+2];
					int j=0;
					for (int i = 0; i < n+2; i++) {
						if (i<=K) {verticalforce[i]= force[2*j];}
						else 	 {verticalforce[i]= force[2*j+1];}
						j++;
					}
		
			//Printing Vertical Force
			for (int i = 0; i < n+2; i++) {
				System.out.println(verticalforce[i]);
			} System.out.println();
		
		
	//Finding Final Equation
			
			//Value of x
			float valueofx[] = new float[n+2];
			valueofx[0]=0;
			for (int i = 0; i < n+1; i++) {
				for (int m = 0; m < i+1; m++) {
					valueofx[i+1]+=L[m];
					
					}System.out.println("Value of x: "+valueofx[i+1]);
			}
			
			float [][][] array1= new float[n+1][4][4];		
			
			for (int i = 0; i < n+1; i++) {
				array1[i][0][0] = valueofx[i]*valueofx[i]*valueofx[i];
				array1[i][0][1] = valueofx[i]*valueofx[i];
				array1[i][0][2] = valueofx[i];
				array1[i][0][3] = 1;
				array1[i][1][0] = 3*valueofx[i]*valueofx[i];
				array1[i][1][1] = 2*valueofx[i];
				array1[i][1][2] = 1;
				array1[i][1][3] = 0;
				array1[i][2][0] = valueofx[i+1]*valueofx[i+1]*valueofx[i+1];
				array1[i][2][1] = valueofx[i+1]*valueofx[i+1];
				array1[i][2][2] = valueofx[i+1];
				array1[i][2][3] = 1;
				array1[i][3][0] = 3*valueofx[i+1]*valueofx[i+1];
				array1[i][3][1] = 2*valueofx[i+1];
				array1[i][3][2] = 1;
				array1[i][3][3] = 0;	
			}

			float inv[][] = new float[n+4][n+4];
			float abcd [][] = new float[n+1][4];

			for (int i = 0; i < n+1; i++) {
				float [][] matrix = {{array1[i][0][0],array1[i][0][1],array1[i][0][2],array1[i][0][3]} , {array1[i][1][0],array1[i][1][1],array1[i][1][2],array1[i][1][3]} , {array1[i][2][0],array1[i][2][1],array1[i][2][2],array1[i][2][3]} , {array1[i][3][0],array1[i][3][1],array1[i][3][2],array1[i][3][3]}};
				inv = Inverse.invert(matrix);
				
					for (int r = 0; r < 4; r++) {
						for (int r2 = 0; r2 < 4; r2++) {
							System.out.print("Inverse : ");
							System.out.print(inv[r][r2]+"  ");
						} System.out.println();
					}System.out.println();
					
							int r=0;
							for (int m2 = 0; m2 < 4; m2++) {
								
								if (i<K) {r=2*i;}
								else {r=2*i+1;}
								
							
								for (int m3 = 0; m3 < 4; m3++) {
									if (i==K&&m3==0) {r=2*K+2;}
									if (i==K&&m3==1) {r=2*K+1;}
									if (i==K&&m3==2) {r=2*K+3;}
									abcd[i][m2]+= inv[m2][m3]*displacement[r];
									System.out.println("ABCD value for "+ i+"and "+r+" is :"+abcd[i][m2]);
									
									r++;
								}
							}
		
			}
			
			
			System.out.println();
			float Scalefactor = displacement[2*K]-displacement[2*K+2];
			System.out.println("Values of Scalefactor: " + Scalefactor);  System.out.println(); 
			
			valueofy = new double[101*n+101] ;
			double X1;
			int r=0;
			
			for (int i1 = 0; i1 < n+1; i1++) {
					 X1= valueofx[i1];
					 
					 while (X1<=valueofx[i1+1]) {					
							valueofy[r] = (X1*X1*X1*abcd[i1][0]+X1*X1*abcd[i1][1]+X1*abcd[i1][2]+abcd[i1][3])/Scalefactor;
							System.out.println("Values of x "+X1+" for y: " + valueofy[r]);	
							X1=X1+0.01*L[i1];
							r++;
							
					}
				
			}
		
			for (int e= 0; e < 101*n+101; e++) {
				ar[0][e] = valueofy[e];
		
			}
		
		
		
		}
	
		
		return ar;
	}
	

}
      
   
  
  








