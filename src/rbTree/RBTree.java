package rbTree;


public class RBTree<K extends Comparable, V> {
    public static final Boolean BLACK = false;
    public static final Boolean RED = true;

    private RBNode root;

    /**
     * 左旋
     * *           p                              pr
     * *        /    \                          /    \
     * *      pl     pr        =====>         p       rr
     * *            /  \                     /   \
     * *           rl  rr                   pl    rl
     * 取下右子节点的左节点  连接到当前节点的右节点
     * 将当前节点的父节点与右子节点关联
     * 当前节点作为右子节点的左节点
     *
     * @param node
     */
    public void leftRotation(RBNode<K, V> node) {
        if (node == null) return;
        RBNode<K, V> r = node.right;
        node.right = r.left;
        if (r.left != null) {
            r.left.parent = node;
        }
        r.parent = node.parent;
        if (node.parent == null) {
            root = r;
        } else if (node.parent.left == node) {
            node.parent.left = r;
        } else {
            node.parent.right = r;
        }
        r.left = node;
        node.parent = r;
    }

    /**
     * 右旋
     * *        p                                 pl
     * *     /    \                            /     \
     * *    pl      pr     ===>             ll        p
     * *  /    \                                    /   \
     * * ll     lr                                 lr     pr
     *
     * @param node
     */
    public void rightRotation(RBNode<K, V> node) {
        if (node == null) return;
        RBNode<K, V> l = node.left;
        node.left = l.right;
        if (l.right != null) {
            l.right.parent = node;
        }
        if (node.parent == null) {
            root = l;
        } else if (node.parent.left == node) {
            node.parent.left = l;
        } else {
            node.parent.right = l;
        }
        l.right = node;
        node.parent = l;
    }


    public void insert(K key, V value) {
        RBNode t = root;
        if (t == null) {
            root = new RBNode(key, value == null ? key : value, null);
            return;
        }
        int cpt;
        RBNode parent;
        do {
            parent = t;
            cpt = key.compareTo(t.key);
            if (cpt > 0) {
                t = t.right;
            } else if (cpt < 0) {
                t = t.left;
            } else {
                t.setValue(value);
                return;
            }
        } while (t != null);
        RBNode<K, V> node = new RBNode(key, value == null ? key : value);
        if (cpt > 0) {
            parent.right = node;
        } else {
            parent.left = node;
        }
        node.parent = parent;
        fixAfterInsert(node);
    }

    private RBNode parentOf(RBNode node) {
        return node == null ? null : node.parent;
    }

    private RBNode rightOf(RBNode node) {
        return node == null ? null : node.right;
    }

    private RBNode leftOf(RBNode node) {
        return node == null ? null : node.left;
    }

    private boolean colorOf(RBNode node) {
        return node == null ? BLACK : node.color;
    }

    private void setColor(RBNode node,boolean color){
        if(node != null) node.color = color;
    }

    /**
     * 1、2-3-4树：新增元素+2节点合并（节点中有一个元素）= 3节点（节点中有两元素）
     * *  红黑树：新增红色节点+父节点为黑色节点  ==> 不需要调整
     *
     * 2、2-3-4树：新增元素+3节点合并（节点中有两个元素）=4节点（节点中有三元素）
     * *   红黑树：这里有4种小情况（左3、右3、还有两个左中右不需要调整）
     * *           新增红色节点+上黑下红=排序后中间节点为黑色，两边节点都是红色
     *
     * 3、2-3-4树：新增一个元素+4节点合并=原来的节点分裂，中间元素升级为父节点，新增元素与剩下其中一个合并
     * *   红黑树：新增红色节点+爷爷节点黑，父亲与叔叔节点都是红色=爷爷节点变红，父亲与叔叔变黑，如果爷爷师根节点，再变黑
     * @param node
     */
    private void fixAfterInsert(RBNode node) {
        node.color = RED;

        while(node != null && node != root && node.parent.color == RED){
            //如果父亲是爷爷的左孩子
            if(parentOf(node) == leftOf(parentOf(parentOf(node)))){
                //叔叔节点
                RBNode u = rightOf(parentOf(parentOf(node)));

                if(colorOf(u) == RED){
                    setColor(parentOf(node),BLACK);
                    setColor(u,BLACK);
                    setColor(parentOf(parentOf(node)),RED);
                    node = parentOf(parentOf(node));
                }else{
                    //如果是父节点的右节点,先左旋一下，将节点与父节点换一个位置
                    if(node == parentOf(node).right){
                        node = parentOf(node);
                        leftRotation(node);
                    }

                    setColor(parentOf(node),BLACK);
                    setColor(parentOf(parentOf(node)),RED);
                    rightRotation(parentOf(parentOf(node)));
                }
            }else{

                //叔叔节点
                RBNode u = leftOf(parentOf(parentOf(node)));

                if(colorOf(u) == RED){
                    setColor(parentOf(node),BLACK);
                    setColor(u,BLACK);
                    setColor(parentOf(parentOf(node)),RED);
                    node = parentOf(parentOf(node));
                }else{
                    //如果是父节点的左节点,先右旋一下，将节点与父节点换一个位置
                    if(node == parentOf(node).left){
                        node = parentOf(node);
                        rightRotation(node);
                    }

                    setColor(parentOf(node),BLACK);
                    setColor(parentOf(parentOf(node)),RED);
                    leftRotation(parentOf(parentOf(node)));
                }
            }
        }
        root.color = BLACK;

    }

    public RBNode getRoot() {
        return root;
    }

    public void setRoot(RBNode root) {
        this.root = root;
    }

    static class RBNode<K, V> {

        private K key;
        private V value;
        private RBNode<K, V> left;
        private RBNode<K, V> right;
        private RBNode<K, V> parent;
        private boolean color;

        public RBNode() {
        }

        public RBNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public RBNode(K key, V value, RBNode parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        public RBNode<K, V> getLeft() {
            return left;
        }

        public void setLeft(RBNode<K, V> left) {
            this.left = left;
        }

        public RBNode<K, V> getRight() {
            return right;
        }

        public void setRight(RBNode<K, V> right) {
            this.right = right;
        }

        public RBNode<K, V> getParent() {
            return parent;
        }

        public void setParent(RBNode<K, V> parent) {
            this.parent = parent;
        }

        public boolean isColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

}
