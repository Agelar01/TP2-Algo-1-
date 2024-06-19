package aed;

public interface Diccionario<String, T> {
    
    public boolean esta(String clave);

    public void definir(String clave, T valor);

    public T obtener(String clave);

    public void eliminar(String clave);

    public int tamaño();

}
