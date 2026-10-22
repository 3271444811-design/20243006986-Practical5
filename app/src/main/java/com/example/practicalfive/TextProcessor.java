package com.example.practicalfive;

public class TextProcessor {

    public String processMunicipality(String municipality) {
        if (municipality == null) {
            return null;
        }

        String trimmedMunicipality = municipality.trim();

        if (trimmedMunicipality.isEmpty()) {
            return null;
        }

        if (trimmedMunicipality.equalsIgnoreCase("beijing")) {
            return "Beijing";
        } else if (trimmedMunicipality.equalsIgnoreCase("shanghai")) {
            return "Shanghai";
        } else if (trimmedMunicipality.equalsIgnoreCase("tianjin")) {
            return "Tianjin";
        } else if (trimmedMunicipality.equalsIgnoreCase("chongqing")) {
            return "Chongqing";
        } else {
            return null;
        }
    }
}
