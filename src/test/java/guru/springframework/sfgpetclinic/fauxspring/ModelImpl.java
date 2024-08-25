package guru.springframework.sfgpetclinic.fauxspring;

import java.util.HashMap;


public class ModelImpl implements Model{
 HashMap<String,Object> vetAttributes= new HashMap<>();
    @Override
    public void addAttribute(String key, Object o) {
        vetAttributes.put(key, o);
    }

    @Override
    public void addAttribute(Object o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addAttribute'");
    }

    public HashMap<String, Object> getVetAttributes() {
        return vetAttributes;
    }

    
}
