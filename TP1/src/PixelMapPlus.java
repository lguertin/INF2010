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
		for(int j = 0; j < height; j++) {
			for(int i = 0; i < width; i++) {
				imageData[j][i] = imageData[j][i].Negative();
			}
		}	
	}
	
	/**
	 * Convertit l'image vers une image en noir et blanc
	 */
	public void convertToBWImage()
	{
		for(int j = 0; j < height; j++) {
			for(int i = 0; i < width; i++) {
				imageData[j][i] = imageData[j][i].toBWPixel();
			}
		}
	}
	
	/**
	 * Convertit l'image vers un format de tons de gris
	 */
	public void convertToGrayImage()
	{
		for(int j = 0; j < height; j++) {
			for(int i = 0; i < width; i++) {
				imageData[j][i] = imageData[j][i].toGrayPixel();
			}
		}
	}
	
	/**
	 * Convertit l'image vers une image en couleurs
	 */
	public void convertToColorImage()
	{
		for(int j = 0; j < height; j++) {
			for(int i = 0; i < width; i++) {
				imageData[j][i] = imageData[j][i].toColorPixel();
			}
		}
	}
	
	public void convertToTransparentImage()
	{
		for(int j = 0; j < height; j++){
			for(int i = 0; i < width; i++){
				imageData[j][i] = imageData[j][i].toTransparentPixel();
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
		AbstractPixel[][] imageDataCopy = new AbstractPixel[height][width]; // Creation d'une nouvelle image vide
		
		for(int j = 0; j < height; j++){
			for(int i = 0; i < width; i++){
				int calculX = (int)(Math.cos(angleRadian)*i+Math.sin(angleRadian)*j-Math.cos(angleRadian)*x-Math.sin(angleRadian)*y+x); // Calcul selon la matrice du point x de depart
				int calculY = (int)(-Math.sin(angleRadian)*i+Math.cos(angleRadian)*j+Math.sin(angleRadian)*x-Math.cos(angleRadian)*y+y); // Calcul selon la matrice du point y de depart
				if(calculX >= 0 && calculX < width && calculY >= 0 && calculY < height)
					imageDataCopy[j][i] = imageData[calculY][calculX];
			}
		}
		
		imageData = imageDataCopy;
		replaceNullPixels();
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
		
		double ratioY = height*1.0/h;	// Calcul du ratio de l'image en Y
		double ratioX = width*1.0/w; 	// Calcul du ratio de l'image en X
		
		AbstractPixel[][] imageDataCopy = new AbstractPixel[h][w];	// Creation d'une nouvelle image vide
		
		for(int j = 0 ; j < h; j++) {
			for(int i = 0; i < w; i++){
				imageDataCopy[j][i] = imageData[(int)(j*ratioY)][(int)(i*ratioX)];
			}
		}
		
		imageData = imageDataCopy;
		this.height = h;
		this.width = w;
		
		replaceNullPixels();
	}
	
	/**
	 * Insert pm dans l'image a la position row0 col0
	 */
	public void inset(PixelMap pm, int row0, int col0)
	{
		if(row0 < 0 || col0 < 0)
			throw new IllegalArgumentException();
		
		for(int j = row0; j < row0+pm.getHeight(); j++){
			for(int i = col0; i < col0+pm.getWidth(); i++){
				if(j < height && i < width)
					imageData[j][i] = pm.imageData[j-row0][i-col0];
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
		
		AbstractPixel[][] imageDataCopy = new AbstractPixel[h][w];	// Creation d'une nouvelle image vide
		for(int j = 0; j < h; j++){
			for(int i = 0 ; i < w; i++){
				if( j < height && i < width)
					imageDataCopy[j][i] = imageData[j][i];
			}
		}
		imageData = imageDataCopy;
		this.height = h;
		this.width = w;
		
		replaceNullPixels();
	}
	
	/**
	 * Effectue une translation de l'image 
	 */
	public void translate(int rowOffset, int colOffset)
	{
		AbstractPixel[][] imageDataCopy = new AbstractPixel[height][width];	// Creation d'une nouvelle image vide
		
		for(int j= 0; j < height;j++){
			for(int i = 0;i< width;i++){
				int newX = i + rowOffset;
				int newY = j + colOffset;
				if(newY<height && newX<width && newY >= 0 && newX >= 0)
					imageDataCopy[newY][newX] = imageData[j][i];
			}
		}	
		imageData = imageDataCopy;
		
		replaceNullPixels();
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
		
		int newHeight = (int)(height/zoomFactor);	// Calcul de la nouvelle taille en Y
		int newWidth = (int)(width/zoomFactor);		// Calcul de la nouvelle taille en X
		
		if(x < newWidth/2)			// Deplacement du zoom s'il est trop a gauche de l'image
			x=newWidth/2;
		if(x > width-newWidth/2)	// Deplacement du zoom s'il est trop a droite de l'image
			x=width-newWidth/2;
		if(y < newHeight/2)			// Deplacement du zoom s'il est trop haut de l'image
			y=newHeight/2;
		if(y > height-newHeight/2)	// Deplacement du zoom s'il est plus bas que l'image
			y=height-newHeight/2;
		
		AbstractPixel[][] imageDataCopy = new AbstractPixel[newHeight][newWidth]; // Creation d'une nouvelle image vide

		for(int j = 0; j < newHeight; j++) {
			for(int i = 0; i < newWidth; i++) {
				imageDataCopy[j][i] = imageData[y-newHeight/2 + j][x-newWidth/2 + i];
			}
		}
		imageData = imageDataCopy;
		width = newWidth;
		height = newHeight;
		
		this.resize((int)(this.width*zoomFactor), (int)(this.height*zoomFactor)); //remet l'image aux bonnes dimensions
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
				if(imageData[j][i].compareTo(min) != -1 && imageData[j][i].compareTo(max) != 1) {
					imageData[j][i] = newPixel;
				}
			}
		}
		
	}

	/**
	 * Effectue une inversion de l'image de haut en bas
	 */
	public void inverser() {
		AbstractPixel[][] imageDataCopy = new AbstractPixel[height][width];	// Creation d'une nouvelle image vide
		
		for(int j = 0; j < height; j++){
			for(int i = 0; i < width; i++){
				imageDataCopy[j][i] = imageData[height-j-1][i];
			}
		}
		imageData = imageDataCopy;
	}
	
	/**
	 * Effectue un remplacement de tout les pixels dont la valeur null avec un pixel du meme type d'image
	 */
	public void replaceNullPixels() {
		for(int row=0; row<height; row++)
		{
			for(int col=0; col<width; col++)
			{
				if(imageData[row][col] == null) {
					if( imageType == ImageType.BW )
						imageData[row][col] = new BWPixel();
					else if( imageType == ImageType.Gray)
						imageData[row][col] = new GrayPixel();
					else if( imageType == ImageType.Color )			
						imageData[row][col] = new ColorPixel();
					else
						imageData[row][col] = new TransparentPixel();
				}
			}
		}
	}
}
