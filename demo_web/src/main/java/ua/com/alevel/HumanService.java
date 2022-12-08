package ua.com.alevel;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HumanService {

    private final HumanRepository humanRepository;
    public HumanService(HumanRepository humanRepository) {
        this.humanRepository = humanRepository;
    }

    public void create(Human human) {
        this.humanRepository.save(human);
    }

    public void delete(Integer id) {
        this.humanRepository.deleteById(id);
    }

    public List<Human> getAll() {
        return humanRepository.findAll();
    }
}
