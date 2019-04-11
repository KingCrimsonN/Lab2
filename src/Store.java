import java.util.ArrayList;

public class Store {
    ArrayList<Department> departments = new ArrayList<>();

    public void add(String name) {
        departments.add(new Department(name));
    }

    public void edit(Department dep, String name) {
        for (int i = 0; i < departments.size(); i++) {
            if (departments.get(i).equals(dep.name))
                departments.get(i).name = name;
        }
    }

    public void remove(Department dep) {
        departments.remove(dep);
    }

    public Department getDepartment(int index) {
        return departments.get(index);
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < departments.size(); i++) {
            s += departments.get(i).toString();
        }
        return s;
    }
}
