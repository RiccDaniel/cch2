interface ExpensiveObject {
        void operation();
    }
    
    class ExpensiveObjectImpl implements ExpensiveObject {
        @Override
        public void operation() {
            System.out.println("Executando operação em ExpensiveObject");
        }
    }
    
    class ExpensiveObjectProxy implements ExpensiveObject {
    
        private ExpensiveObjectImpl expensiveObject;
        private User user;
    
        public ExpensiveObjectProxy(ExpensiveObjectImpl expensiveObject, User user) {
            this.expensiveObject = expensiveObject;
            this.user = user;
        }
    
        @Override
        public void operation() {
            if (user.isAdmin()) {
                expensiveObject.operation();
            } else {
                System.out.println("O usuário não tem permissão para acessar o ExpensiveObject");
            }
        }
    }
    
    class User {
        private String name;
        private boolean isAdmin;
    
        public User(String name, boolean isAdmin) {
            this.name = name;
            this.isAdmin = isAdmin;
        }
    
        public String getName() {
            return name;
        }
    
        public boolean isAdmin() {
            return isAdmin;
        }
    }
    
    public class Main {
        public static void main(String[] args) {
            ExpensiveObjectImpl expensiveObject = new ExpensiveObjectImpl();
            User user1 = new User("João", true);
            User user2 = new User("Maria", false);
    
            ExpensiveObjectProxy proxy1 = new ExpensiveObjectProxy(expensiveObject, user1);
            ExpensiveObjectProxy proxy2 = new ExpensiveObjectProxy(expensiveObject, user2);
    
            proxy1.operation(); // O usuário João tem permissão, então a operação é executada
            proxy2.operation(); // A usuária Maria não tem permissão, então a mensagem de erro é exibida
        }
    }
    

