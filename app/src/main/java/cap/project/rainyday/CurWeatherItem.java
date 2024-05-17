package cap.project.rainyday;

// WeatherItem.java
public class CurWeatherItem {
    private String time;
    private String temperature;
    private String rainAmount;
    private String rainPercent;
    private int iconResId;
    private String weatherInfo;

    public CurWeatherItem(String time, String temperature, String rainAmount, String rainPercent, int iconResId, String weatherInfo) {
        this.time = time;
        this.temperature = temperature;
        this.rainAmount = rainAmount;
        this.rainPercent = rainPercent;
        this.iconResId = iconResId;
        this.weatherInfo = weatherInfo;
    }

    public String getTime() {
        return time;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getRainAmount() {
        return rainAmount;
    }

    public String getRainPercent() {
        return rainPercent;
    }

    public int getIconResId() {
        return iconResId;
    }

    public String getWeatherInfo() {
        return weatherInfo;
    }
}

