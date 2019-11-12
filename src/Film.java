import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author MiklosMayer
 */
public class Film implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String title;
	private String director;
	private int runningTime;
	private double purchaseCost;
	
	public Film() {
		title = "";
		director = "";
		runningTime = 0;
		purchaseCost = 0.00;
	}
	
	/**
	 * @param title of the film
	 * @param director of the film
	 * @param runningTime of the film in minutes
	 * @param purchaseCost of the film
	 */
	public Film(String title, String director, int runningTime, double purchaseCost) {
		super();
		this.title = title;
		this.director = director;
		this.runningTime = runningTime;
		this.purchaseCost = purchaseCost;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}

	public double getPurchaseCost() {
		return purchaseCost;
	}

	public void setPurchaseCost(double purchaseCost) {
		this.purchaseCost = purchaseCost;
	}

	/**
	 *	@return The <a href="https://docs.oracle.com/javase/9/docs/api/java/lang/String.html">String</a> representation of the class.
	 */
	@Override
	public String toString() {
		NumberFormat formatter = new DecimalFormat("#0.00");
		return "Film [title = " + title + ", director = " + director
				+ ", runningTime = " + runningTime
				+ " minutes, purchaseCost = �"
				+ formatter.format(purchaseCost) + "]";
	}
}