public interface Diccionario {
    
    public boolean está(T clave);

    public void definir(T clave, T valor);

    public T obtener(T clave);

    public void borrar(T clave);

    public int tamaño();

}
