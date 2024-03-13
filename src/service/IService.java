package service;

public interface IService<E> {
    void add() throws InterruptedException;
    void edit() throws InterruptedException;
    void delete() throws InterruptedException;
    void findByName() throws InterruptedException;
    void findByType() throws InterruptedException;
    void showAllProduct() throws InterruptedException;



}
