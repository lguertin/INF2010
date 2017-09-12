/**
 * Classe de pixel en couleurs 
 * @author :
 * @date : 
 */

public class ColorPixel extends AbstractPixel
{
	public int[] rgb; // donnees de l'image
	
	/**
	 * Constructeur par defaut (pixel blanc)
	 */
	ColorPixel()
	{
		rgb = new int[3];
		rgb[0] = 255;
		rgb[1] = 255;
		rgb[2] = 255;
	}
	
	/**
	 * Assigne une valeur au pixel
	 * @param rgb: valeurs a assigner 
	 */
	ColorPixel(int[] rgb)
	{
		this.rgb = new int[3];
		this.rgb[0] = rgb[0];
		this.rgb[1] = rgb[1];
		this.rgb[2] = rgb[2];
	}
	
	/**
	 * Renvoie un pixel copie de type noir et blanc
	 */
	public BWPixel toBWPixel()
	{
		return (new BWPixel((rgb[0]+rgb[1]+rgb[2])/3 > 127 ? true : false));
	}
	
	/**
	 * Renvoie un pixel copie de type tons de gris
	 */
	public GrayPixel toGrayPixel()
	{
		return (new GrayPixel((rgb[0]+rgb[1]+rgb[2])/3));	
	}
	
	/**
	 * Renvoie un pixel copie de type couleurs
	 */
	public ColorPixel toColorPixel()
	{
		return (new ColorPixel(this.rgb));
	}
	
	public TransparentPixel toTransparentPixel()
	{
		int[] rgba = new int[] {rgb[0], rgb[1], rgb[2], 255};
		return (new TransparentPixel(rgba));
	}
	
	/**
	 * Renvoie le negatif du pixel (255-pixel)
	 */
	public AbstractPixel Negative()
	{
		int[] rgb2 =  {255-this.rgb[0], 255-this.rgb[1], 255-this.rgb[2]};
		return (new ColorPixel(rgb2));
	}
	
	public void setAlpha(int alpha)
	{
		//ne fait rien
	}
	
	/**
	 * Convertit le pixel en String (sert a ecrire dans un fichier 
	 * (avec un espace supplémentaire en fin)s
	 */
	public String toString()
	{
		return  ((Integer)rgb[0]).toString() + " " + 
				((Integer)rgb[1]).toString() + " " +
				((Integer)rgb[2]).toString() + " ";
	}
	
	public int compareTo(AbstractPixel p) {
		if (rgb[0] < ((ColorPixel) p).rgb[0]
				&& rgb[1] < ((ColorPixel) p).rgb[1]
				&& rgb[2] < ((ColorPixel) p).rgb[2]) {
			return -1;
		} else {
			if (rgb[0] == ((ColorPixel) p).rgb[0]
					&& rgb[1] == ((ColorPixel) p).rgb[1]
					&& rgb[2] == ((ColorPixel) p).rgb[2]) {
				return 0;
			} else {
				return 1;
			}
		}
	}

}