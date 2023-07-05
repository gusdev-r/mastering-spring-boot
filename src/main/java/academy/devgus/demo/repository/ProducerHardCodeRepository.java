package academy.devgus.demo.repository;

import academy.devgus.demo.domain.Producer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import test.outside.Connection;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Log4j2

public class ProducerHardCodeRepository {
    private ProducerData producerData;
    @Qualifier(value = "mySQL") //it maps all and find the bean called mySQL(by the method or name)
    private final Connection connection;



    public List<Producer> findAll() {
        return producerData.getProducers();
    }
    public Producer save(Producer producer) {
        producerData.getProducers().add(producer);
        return producer;
    }
    public Optional<Producer> findById(Long id) {
        return producerData.getProducers().stream().filter(producer -> producer.getId().equals(id)).findFirst();
    }
    public List<Producer> findByName(String name) {
        return producerData.getProducers().stream().filter(producer -> producer.getName().equalsIgnoreCase(name)).toList();
    }
    public void delete(Producer producer) {
        producerData.getProducers().remove(producer);
    }
    public void update(Producer producer) {
        delete(producer);
        save(producer);
    }
}
