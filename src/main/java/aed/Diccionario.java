package aed;
public interface Diccionario<T> {
    
    public boolean está(T clave);

    public void definir(T clave, T valor);

    public T obtener(T clave);

    public void eliminar(T clave);

    public int tamaño();

}
