public class WindConverter {

    public static String convertWindDegToDirection(double directionInDegrees) {
        if (directionInDegrees > 348.75 || directionInDegrees <= 11.25) {
            return "N";
        } else if (directionInDegrees > 11.25 && directionInDegrees <= 33.25) {
            return "NNE";
        } else if (directionInDegrees > 33.75 && directionInDegrees <= 56.25) {
            return "NE";
        } else if (directionInDegrees > 56.25 && directionInDegrees <= 78.75) {
            return "ENE";
        } else if (directionInDegrees > 78.75 && directionInDegrees <= 101.25) {
            return "E";
        } else if (directionInDegrees > 101.25 && directionInDegrees <= 123.75) {
            return "ESE";
        } else if (directionInDegrees > 123.75 && directionInDegrees <= 146.25) {
            return "SE";
        } else if (directionInDegrees > 146.25 && directionInDegrees <= 168.75) {
            return "SSE";
        } else if (directionInDegrees > 168.75 && directionInDegrees <= 191.25) {
            return "S";
        } else if (directionInDegrees > 191.25 && directionInDegrees <= 213.75) {
            return "SSW";
        } else if (directionInDegrees > 213.75 && directionInDegrees <= 236.25) {
            return "SW";
        } else if (directionInDegrees > 236.25 && directionInDegrees <= 258.75) {
            return "WSW";
        } else if (directionInDegrees > 258.75 && directionInDegrees <= 281.25) {
            return "W";
        } else if ((directionInDegrees >= 281.25) && (directionInDegrees <= 303.75)) {
            return "WNW";
        } else if ((directionInDegrees >= 303.75) && (directionInDegrees <= 326.25)) {
            return "NW";
        } else if (directionInDegrees >= 326.25) {
            return "NNW";
        } else {
            return "?";
        }
    }
}
