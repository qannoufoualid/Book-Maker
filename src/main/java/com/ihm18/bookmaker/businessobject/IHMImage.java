package com.ihm18.bookmaker.businessobject;

import java.io.File;
import java.time.LocalDateTime;

/**
 * L'entité IHMImage
 * @author qannoufoualid
 *
 */
public class IHMImage {

	
	/**
	 * L'id de l'image
	 */
	private Long id;
	/**
	 * Le nom de l'image.
	 */
	private String title;
	/**
	 * La description de l'image
	 */
	private String description;
	/**
	 * La date de création de l'image
	 */
	private LocalDateTime dateCreation;
	/**
	 * Le fichier de l'image.
	 */
	private File file;
	
	/**
	 * page de l'image
	 */
	private Page page;
	
	/**
	 * coordonnée sur l'axe X
	 */
	private double x;
	/**
	 * coordonnée sur l'axe Y
	 */
	private double y;
	
	/**
	 * Largeur de l'image
	 */
	private double width;
	/**
	 * Hauteur de l'image
	 */
	private double height;

	/**
	 * Style de la bordure
	 */
	private String borderStyle;

	/**
	 * Taille de la bordure
	 */
	private String borderSize;

	/**
	 * Couleur de la bordure
	 */
	private String borderColor;

	/**
	 * Luminosité
	 */
	private double brightness = Double.MIN_VALUE;
	
	/**
	 * Sepia
	 */
	private double sepia = Double.MIN_VALUE;
	/**
	 * Saturation
	 */
	private double saturation = Double.MIN_VALUE;
	
	/**
	 * getter de fichier de l'image
	 * @return
	 */
	public File getFile() {
		return file;
	}

	/**
	 * setter de fichier de l'image.
	 * @param file
	 */
	public void setFile(File file) {
		this.file = file;
	}


	
	/**
	 * getter de la page de l'image.
	 * @return
	 */
	public Page getPage() {
		return page;
	}

	/**
	 * setter de la page de l'image.
	 * @return
	 */
	public void setPage(Page page) {
		this.page = page;
	}

	/**
	 * construire une IHMImage
	 * @param name nom de l'image
	 * @param description description de l'image
	 * @param dateCreation date de craation de l'image
	 */
	public IHMImage(String name, String description, LocalDateTime dateCreation) {
		this.id = System.currentTimeMillis();
		this.title = name;
		this.description = description;
		this.dateCreation = dateCreation;
	}
	
	/**
	 * getter de l'id de l'image
	 * @return l'id de l'image
	 */
	public Long getId() {
		return id;
	}
	/**
	 * setter de l'id de l'image
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * getter du titre de l'image
	 * @return le  titre de l'image
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * setter du  titre de l'image
	 * @param name  titre de l'image
	 */
	public void setTitle(String name) {
		this.title = name;
	}
	/**
	 * getter de la description de l'image
	 * @return la description de l'image
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * setter de la description de l'image
	 * @param description la description de l'image
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * getter de la date de création d'une image.
	 * @return la date de création d'une image.
	 */
	public LocalDateTime getDateCreation() {
		return dateCreation;
	}
	/**
	 * setter de la date de création d'une image.
	 * @param dateCreation la date de création d'une image.
	 */
	public void setDateCreation(LocalDateTime dateCreation) {
		this.dateCreation = dateCreation;
	}

	
	
	/**
	 * getter de x
	 * @return x x
	 */
	public double getX() {
		return x;
	}

	/**
	 * setter de x
	 * @param x
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * getter de y
	 * @return y
	 */
	public double getY() {
		return y;
	}

	/**
	 * setter de y
	 * @param y y
	 */
	public void setY(double y) {
		this.y = y;
	}

	
	
	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public String getBorderStyle() {
		return borderStyle;
	}

	public void setBorderStyle(String borderStyle) {
		this.borderStyle = borderStyle;
	}

	public String getBorderSize() {
		return borderSize;
	}

	public void setBorderSize(String borderSize) {
		this.borderSize = borderSize;
	}

	public String getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

	public double getBrightness() {
		return brightness;
	}

	public void setBrightness(double brightness) {
		this.brightness = brightness;
	}

	public double getSepia() {
		return sepia;
	}

	public void setSepia(double sepia) {
		this.sepia = sepia;
	}

	public double getSaturation() {
		return saturation;
	}

	public void setSaturation(double saturation) {
		this.saturation = saturation;
	}

	@Override
	public String toString() {
		return "IHMImage [id=" + id + ", title=" + title + ", description=" + description + ", dateCreation="
				+ dateCreation + ", file=" + file + ", x=" + x + ", y=" + y + ", width=" + width
				+ ", height=" + height + ", borderStyle=" + borderStyle + ", borderSize=" + borderSize
				+ ", borderColor=" + borderColor + ", brightness=" + brightness + ", sepia=" + sepia + ", saturation="
				+ saturation + "]";
	}
	
	
	
	
}
