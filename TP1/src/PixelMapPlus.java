import java.awt.PageAttributes.ColorType;

/**
 * Classe PixelMapPlus
 * Image de type noir et blanc, tons de gris ou couleurs
 * Peut lire et ecrire des fichiers PNM
 * Implemente les methodes de ImageOperations
 * @author : 
 * @date   : 
 */

public class PixelMapPlus extends PixelMap implements ImageOperations 
{
	/**
	 * Constructeur creant l'image a partir d'un fichier
	 * @param fileName : Nom du fichier image
	 */
	PixelMapPlus(String fileName)
	{
		super( fileName );
	}
	
	/**
	 * Constructeur copie
	 * @param type : type de l'image a creer (BW/Gray/Color/Transparent)
	 * @param image : source
	 */
	PixelMapPlus(PixelMap image)
	{
		super(image); 
	}
	
	/**
	 * Constructeur copie (sert a changer de format)
	 * @param type : type de l'image a creer (BW/Gray/Color/Transparent)
	 * @param image : source
	 */
	PixelMapPlus(ImageType type, PixelMap image)
	{
		super(type, image); 
	}
	
	/**
	 * Constructeur servant a allouer la memoire de l'image
	 * @param type : type d'image (BW/Gray/Color/Transparent)
	 * @param h : hauteur (height) de l'image 
	 * @param w : largeur (width) de l'image
	 */
	PixelMapPlus(ImageType type, int h, int w)
	{
		super(type, h, w);
	}
	
	/**
	 * Genere le negatif d'une image
	 */
	public void negate()
	{
		for(int j =0; j<height ; j++){
			for(int i = 0;i <width;i++){
				imageData[j][i].Negative();
			}
		}
			
		
	}
	
	/**
	 * Convertit l'image vers une image en noir et blanc
	 */
	public void convertToBWImage()
	{
		for(int j =0; j<height ; j++){
			for(int i = 0;i <width;i++){
				imageData[j][i].toBWPixel();
			}
		}
		
	}
	
	/**
	 * Convertit l'image vers un format de tons de gris
	 */
	public void convertToGrayImage()
	{
		for(int j =0; j<height ; j++){
			for(int i = 0;i <width;i++){
				imageData[j][i].toGrayPixel();
			}
		}
	}
	
	/**
	 * Convertit l'image vers une image en couleurs
	 */
	public void convertToColorImage()
	{
		for(int j =0; j<height ; j++){
			for(int i = 0;i <width;i++){
				imageData[j][i].toColorPixel();
			}
		}
	}
	
	public void convertToTransparentImage()
	{
		for(int j =0; j<height ; j++){
			for(int i = 0;i <width;i++){
				imageData[j][i].toTransparentPixel();
			}
		}
	}
	
	/**
	 * Fait pivoter l'image de 10 degres autour du pixel (row,col)=(0, 0)
	 * dans le sens des aiguilles d'une montre (clockWise == true)
	 * ou dans le sens inverse des aiguilles d'une montre (clockWise == false).
	 * Les pixels vides sont blancs.
	 * @param clockWise : Direction de la rotation 
	 */
	public void rotate(int x, int y, double angleRadian)
	{
		
		
	}
	
	/**
	 * Modifie la longueur et la largeur de l'image 
	 * @param w : nouvelle largeur
	 * @param h : nouvelle hauteur
	 */
	public void resize(int w, int h) throws IllegalArgumentException
	{
		if(w < 0 || h < 0)
			throw new IllegalArgumentException(); 
		if(w < width || h < height )
			return; //Msg pr leandre : voir comment gerer le probleme
		int ratioY = h / height;
		int ratioX = w / width;
		AbstractPixel[][] imageDataCopy = new AbstractPixel[h][w];	
		for(int j =0 ;j<height;j++)
			for(int i = 0; i <width ; i++){
				imageDataCopy[j*ratioY][i*ratioX] = imageData[j][i];
				System.out.println( "W = " +width + " W2=" + w);
			}
	}
	
	/**
	 * Insert pm dans l'image a la position row0 col0
	 */
	public void inset(PixelMap pm, int row0, int col0)
	{
		if(row0 < 0 || col0 < 0)
			throw new IllegalArgumentException();
		
		for(int j= col0; j < col0+pm.getHeight();j++){
			for(int i = row0;i< row0+pm.getWidth();i++){
				if(j<height && i<width)
					imageData[j][i] = pm.imageData[j-col0][i-row0];
			}
		}		
	}
	
	/**
	 * Decoupe l'image 
	 */
	public void crop(int h, int w)
	{
		if(w < 0 || h < 0)
			throw new IllegalArgumentException();
		
		AbstractPixel[][] imageDataCopy = new AbstractPixel[h][w];
		for(int j= 0; j < h;j++){
			for(int i =0;i<w;i++){
				if(j<height && i<width)
					imageDataCopy[j][i] = imageData[j][i];
			}
		}
		imageData = imageDataCopy;
		
	}
	
	/**
	 * Effectue une translation de l'image 
	 */
	public void translate(int rowOffset, int colOffset)
	{
		AbstractPixel[][] imageDataCopy = new AbstractPixel[height][width];	
		
		for(int j= 0; j < height;j++){
			for(int i = 0;i< width;i++){
				int newX = i + rowOffset;
				int newY = j + colOffset;
				if(newY<height && newY<width && newY >= 0 && newX >= 0)
					imageDataCopy[newY][newX] = imageData[j][i];
			}
		}	
		imageData = imageDataCopy;
	}
	
	/**
	 * Effectue un zoom autour du pixel (x,y) d'un facteur zoomFactor 
	 * @param x : colonne autour de laquelle le zoom sera effectue
	 * @param y : rangee autour de laquelle le zoom sera effectue  
	 * @param zoomFactor : facteur du zoom a effectuer. Doit etre superieur a 1
	 */
	public void zoomIn(int x, int y, double zoomFactor) throws IllegalArgumentException
	{
		if(zoomFactor < 1.0)
			throw new IllegalArgumentException();
		
		int newHeight = (int)(height/zoomFactor);
		int newWidth = (int)(width/zoomFactor);
		
		if(y < newHeight/2)
			y=newHeight/2;
		if(y > height-newHeight/2)
			y=height-newHeight/2;
		if(x < newWidth/2)
			x=newWidth/2;
		if(x > width-newWidth/2)
			x=width-newWidth/2;
		
		AbstractPixel[][] imageDataCopy = new AbstractPixel[newHeight][newWidth];

		for(int j=0; j < newHeight; j++){
			for(int i=0; i < newWidth;i++){
				imageDataCopy[j][i] = imageData[y-newHeight/2 + j][x-newWidth/2 + i];
			}
		}
		imageData = imageDataCopy;
		this.width = newWidth;
		this.height = newHeight;
		//System.out.println(x + " " + " " + y + " " + newHeight + " " + " " + newWidth);
	}

	/**
	 * Effectue un remplacement de tout les pixels dont la valeur est entre min et max 
	 * avec newPixel
	 * @param min : La valeur miniale d'un pixel
	 * @param max : La valeur maximale d'un pixel  
	 * @param newPixel : Le pixel qui remplacera l'ancienne couleur 
	 * (sa valeur est entre min et max)
	 */
	public void replaceColor(AbstractPixel min, AbstractPixel max,
			AbstractPixel newPixel) {
		for(int j =0; j<height ; j++){
			for(int i = 0;i <width;i++){
				//System.out.println(j + " " + imageData[j].length);
				if(imageData[j][i].compareTo(min) != -1 && imageData[j][i].compareTo(max) != 1) {
					imageData[j][i] = newPixel;
				}
			}
		}
		
	}

	public void inverser() {
		AbstractPixel[][] imageDataCopy = new AbstractPixel[height][width];
		
		for(int j =0; j<height ; j++){
			for(int i = 0;i <width;i++){
				imageDataCopy[j][i] = imageData[height-j-1][width-i-1];
			}
		}
		imageData = imageDataCopy;
				
	}
}
