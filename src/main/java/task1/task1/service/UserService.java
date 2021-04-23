package task1.task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task1.task1.entity.Users;
import task1.task1.payload.Result;
import task1.task1.payload.UserDto;
import task1.task1.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Result addUser(UserDto userDto) {
        if (userRepository.existsByPhoneNumber(userDto.getPhoneNumber()))
            return new Result("Bunday telefon raqamli ishchi uje mavjud", false);
        Users users = new Users();
        users.setFirstName(userDto.getFirstName());
        users.setLastName(userDto.getLastName());
        users.setPhoneNumber(userDto.getPhoneNumber());
        users.setCode(Integer.toString(userRepository.findAll().size() + 1));
        users.setPassword(userDto.getPassword());
        userRepository.save(users);
        return new Result("Yangi ishchi muvaffaqiyatli saqlandi", true);
    }

    public List<Users> getUsers() {
        return userRepository.findAll();
    }

    public Users getUser(Integer id) {
        Optional<Users> optionalUsers = userRepository.findById(id);
        if (!optionalUsers.isPresent()) return null;
        return optionalUsers.get();
    }

    public Result editUser(Integer id, UserDto userDto) {
        Optional<Users> optionalUsers = userRepository.findById(id);
        if (!optionalUsers.isPresent())
            return new Result("Bunday ishchi bazada mavjud emas", false);
        if (userRepository.existsByPhoneNumber(userDto.getPhoneNumber()))
            return new Result("Bunday telefon raqamli ishchi uje mavjud", false);
        Users users = optionalUsers.get();
        users.setFirstName(userDto.getFirstName());
        users.setLastName(userDto.getLastName());
        users.setPhoneNumber(userDto.getPhoneNumber());
        users.setPassword(userDto.getPassword());
        userRepository.save(users);
        return new Result("Ishchi muvaffaqiyatli taxrirlandi", true);
    }
    public Result deleteUser(Integer id){
        Optional<Users> optionalUsers = userRepository.findById(id);
        if (!optionalUsers.isPresent())
            return new Result("Bunday ishchi bazada mavjud emas", false);
        userRepository.deleteById(id);
        return new Result("Ishchi muvaffaqiyatli o'chirildi", true);
    }
}
