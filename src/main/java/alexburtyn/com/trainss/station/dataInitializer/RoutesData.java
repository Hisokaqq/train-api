package alexburtyn.com.trainss.station.dataInitializer;

import java.util.Arrays;
import java.util.List;

public class RoutesData {
    public static final List<RouteInfo> ROUTES = Arrays.asList(
            new RouteInfo("U1: Oberlaa - Leopoldau", Arrays.asList("Oberlaa", "Neulaa", "Alaudagasse", "Altes Landgut", "Troststraße", "Reumannplatz", "Keplerplatz", "Südtiroler Platz", "Karlsplatz", "Stephansplatz", "Schwedenplatz", "Nestroyplatz", "Praterstern", "Vorgartenstraße", "Donauinsel", "Kaisermühlen", "Alte Donau", "Kagran", "Kagraner Platz", "Rennbahnweg", "Aderklaaer Straße", "Großfeldsiedlung", "Leopoldau")),
            new RouteInfo("U2: Karlsplatz - Seestadt", Arrays.asList("Karlsplatz", "Museumsquartier", "Volkstheater", "Rathaus", "Schottentor", "Schottenring", "Taborstraße", "Praterstern", "Messe-Prater", "Krieau", "Stadion", "Donaumarina", "Donaustadtbrücke", "Stadlau", "Hardeggasse", "Donauspital", "Aspernstraße", "Hausfeldstraße", "Aspern Nord", "Seestadt")),
            new RouteInfo("U3: Ottakring - Simmering", Arrays.asList("Ottakring", "Kendlerstraße", "Hütteldorfer Straße", "Johnstraße", "Schweglerstraße", "Westbahnhof", "Zieglergasse", "Neubaugasse", "Volkstheater", "Herrengasse", "Stephansplatz", "Stubentor", "Landstraße", "Rochusgasse", "Kardinal-Nagl-Platz", "Schlachthausgasse", "Erdberg", "Gasometer", "Zippererstraße", "Enkplatz", "Simmering")),
            new RouteInfo("U4: Hütteldorf - Heiligenstadt", Arrays.asList("Hütteldorf", "Ober St. Veit", "Unter St. Veit", "Braunschweiggasse", "Hietzing", "Schönbrunn", "Meidling Hauptstraße", "Längenfeldgasse", "Margaretengürtel", "Kettenbrückengasse", "Karlsplatz", "Stadtpark", "Landstraße", "Schwedenplatz", "Schottenring", "Rossauer Lände", "Friedensbrücke", "Spittelau", "Heiligenstadt")),
            new RouteInfo("U6: Siebenhirten - Floridsdorf", Arrays.asList("Siebenhirten", "Perfektastraße", "Erlaaer Straße", "Alterlaa", "Am Schöpfwerk", "Tscherttegasse", "Philadelphiabrücke", "Gumpendorfer Straße", "Westbahnhof", "Burggasse-Stadthalle", "Thaliastraße", "Josefstädter Straße", "Alser Straße", "Michelbeuern-AKH", "Währinger Straße-Volksoper", "Nussdorfer Straße", "Spittelau", "Jägerstraße", "Dresdner Straße", "Handelskai", "Neue Donau", "Floridsdorf"))
    );

    public static class RouteInfo {
        public String name;
        public List<String> stations;

        public RouteInfo(String name, List<String> stations) {
            this.name = name;
            this.stations = stations;
        }
    }
}
