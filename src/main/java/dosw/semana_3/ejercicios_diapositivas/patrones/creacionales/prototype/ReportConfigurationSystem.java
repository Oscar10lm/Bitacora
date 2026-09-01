package dosw.semana_3.ejercicios_diapositivas.patrones.creacionales.prototype;

import java.util.ArrayList;
import java.util.List;

public class ReportConfigurationSystem {

    // ==========================================
    // 1. INTERFAZ PROTOTIPO
    // ==========================================
    public interface ConfigPrototype {
        ConfigPrototype cloneConfig();
    }

    // ==========================================
    // 2. PROTOTIPO CONCRETO (La Configuración del Reporte)
    // ==========================================
    public static class ReportConfig implements ConfigPrototype {
        
        private String reportName;
        private String format;
        private List<String> columns;
        private String filter;
        private List<String> permissions;

        // Constructor estándar (Requiere mucha configuración manual)
        public ReportConfig(String reportName, String format, List<String> columns, String filter, List<String> permissions) {
            System.out.println(">>> Configurando reporte desde cero: " + reportName + "...");
            this.reportName = reportName;
            this.format = format;
            this.columns = new ArrayList<>(columns);
            this.filter = filter;
            this.permissions = new ArrayList<>(permissions);
        }

        // Constructor de Copia (Deep Copy para las listas)
        private ReportConfig(ReportConfig target) {
            if (target != null) {
                this.reportName = target.reportName;
                this.format = target.format;
                // Importante: hacer una nueva lista basada en la original para evitar referencias cruzadas
                this.columns = new ArrayList<>(target.columns); 
                this.filter = target.filter;
                this.permissions = new ArrayList<>(target.permissions);
            }
        }

        // ==========================================
        // 3. IMPLEMENTACIÓN DE LA CLONACIÓN
        // ==========================================
        @Override
        public ConfigPrototype cloneConfig() {
            return new ReportConfig(this);
        }

        // Setters para mutar el clon después de crearlo
        public void setReportName(String reportName) {
            this.reportName = reportName;
        }

        public void setFilter(String filter) {
            this.filter = filter;
        }
        
        public void addColumn(String column) {
            this.columns.add(column);
        }

        @Override
        public String toString() {
            return "Reporte: [" + reportName + "]\n" +
                   "  - Formato: " + format + "\n" +
                   "  - Columnas: " + columns + "\n" +
                   "  - Filtro Activo: " + filter + "\n" +
                   "  - Permisos: " + permissions + "\n";
        }
    }

    // ==========================================
    // 4. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        // 1. Crear la configuración base (esto lleva tiempo/trabajo en la vida real)
        List<String> defaultColumns = List.of("ID", "Fecha", "Ingresos", "Gastos", "Balance");
        List<String> defaultPermissions = List.of("Rol_Finanzas", "Rol_Gerencia");
        
        ReportConfig baseConfig = new ReportConfig(
            "Reporte Mensual Estándar (Global)", 
            "PDF/Excel", 
            defaultColumns, 
            "Toda la compañía (Sin filtro geográfico)", 
            defaultPermissions
        );
        
        System.out.println(baseConfig);

        // 2. Un analista necesita el mismo reporte pero SOLO para la Región Norte.
        // En lugar de configurar las 5 columnas, formatos y permisos desde cero, simplemente clona.
        System.out.println("--- Analista: Clonando configuración base para la Región Norte ---");
        ReportConfig norteConfig = (ReportConfig) baseConfig.cloneConfig();
        
        // Mutamos solo lo necesario
        norteConfig.setReportName("Reporte Mensual - Región Norte");
        norteConfig.setFilter("Región = 'Norte'");
        norteConfig.addColumn("Subvención Regional"); // Agregamos una columna extra solo para el norte
        
        System.out.println(norteConfig);

        // 3. Verificamos que la configuración base no fue alterada (gracias al Deep Copy)
        System.out.println("--- Verificando la configuración base original ---");
        System.out.println(baseConfig);
    }
}
