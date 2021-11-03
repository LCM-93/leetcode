package rbTree;


import java.util.TreeMap;

public class RBTree<K extends Comparable, V> {
    public static final Boolean BLACK = false;
    public static final Boolean RED = true;

    private RBNode<K, V> root;

    private TreeMap map;

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

    private <K, V> RBNode<K, V> parentOf(RBNode<K, V> node) {
        return node == null ? null : node.parent;
    }

    private <K, V> RBNode<K, V> rightOf(RBNode<K, V> node) {
        return node == null ? null : node.right;
    }

    private <K, V> RBNode<K, V> leftOf(RBNode<K, V> node) {
        return node == null ? null : node.left;
    }

    private <K, V> boolean colorOf(RBNode<K, V> node) {
        return node == null ? BLACK : node.color;
    }

    private <K, V> void setColor(RBNode<K, V> node, boolean color) {
        if (node != null) node.color = color;
    }

    /**
     * 1、2-3-4树：新增元素+2节点合并（节点中有一个元素）= 3节点（节点中有两元素）
     * *  红黑树：新增红色节点+父节点为黑色节点  ==> 不需要调整
     * <p>
     * 2、2-3-4树：新增元素+3节点合并（节点中有两个元素）=4节点（节点中有三元素）
     * *   红黑树：这里有4种小情况（左3、右3、还有两个左中右不需要调整）
     * *           新增红色节点+上黑下红=排序后中间节点为黑色，两边节点都是红色
     * <p>
     * 3、2-3-4树：新增一个元素+4节点合并=原来的节点分裂，中间元素升级为父节点，新增元素与剩下其中一个合并
     * *   红黑树：新增红色节点+爷爷节点黑，父亲与叔叔节点都是红色=爷爷节点变红，父亲与叔叔变黑，如果爷爷师根节点，再变黑
     *
     * @param node
     */
    private void fixAfterInsert(RBNode<K, V> node) {
        node.color = RED;

        while (node != null && node != root && node.parent.color == RED) {
            //如果父亲是爷爷的左孩子
            if (parentOf(node) == leftOf(parentOf(parentOf(node)))) {
                //叔叔节点
                RBNode<K, V> u = rightOf(parentOf(parentOf(node)));

                if (colorOf(u) == RED) {
                    setColor(parentOf(node), BLACK);
                    setColor(u, BLACK);
                    setColor(parentOf(parentOf(node)), RED);
                    node = parentOf(parentOf(node));
                } else {
                    //如果是父节点的右节点,先左旋一下，将节点与父节点换一个位置
                    if (node == parentOf(node).right) {
                        node = parentOf(node);
                        leftRotation(node);
                    }

                    setColor(parentOf(node), BLACK);
                    setColor(parentOf(parentOf(node)), RED);
                    rightRotation(parentOf(parentOf(node)));
                }
            } else {

                //叔叔节点
                RBNode<K, V> u = leftOf(parentOf(parentOf(node)));

                if (colorOf(u) == RED) {
                    setColor(parentOf(node), BLACK);
                    setColor(u, BLACK);
                    setColor(parentOf(parentOf(node)), RED);
                    node = parentOf(parentOf(node));
                } else {
                    //如果是父节点的左节点,先右旋一下，将节点与父节点换一个位置
                    if (node == parentOf(node).left) {
                        node = parentOf(node);
                        rightRotation(node);
                    }

                    setColor(parentOf(node), BLACK);
                    setColor(parentOf(parentOf(node)), RED);
                    leftRotation(parentOf(parentOf(node)));
                }
            }
        }
        root.color = BLACK;

    }

    /**
     * 找前驱节点
     *
     * @return
     */
    private <K, V> RBNode<K, V> predecessor(RBNode<K, V> node) {
        if (node == null) return null;
        else if (node.left != null) {
            RBNode<K, V> t = node.left;
            while (node.right != null)
                t = node.right;
            return t;
        } else {  //如果左孩子为空，就向上找第一个拐点
            RBNode<K, V> p = node.parent;
            RBNode<K, V> t = node;
            while (p != null && t == p.left) {
                t = p;
                p = t.parent;
            }
            return p;
        }
    }


    /**
     * 找后继节点
     *
     * @return
     */
    private <K, V> RBNode<K, V> successor(RBNode<K, V> node) {
        if (node == null) return null;
        else if (node.right != null) {
            RBNode<K, V> t = node.right;
            while (t.left != null) {
                t = t.left;
            }
            return t;
        } else {
            RBNode<K, V> p = node.parent;
            RBNode<K, V> t = node;
            while (p != null && t == p.right) {
                t = p;
                p = t.parent;
            }
            return p;
        }
    }

    public void delete(K key) {
        RBNode<K, V> node = findNode(key);
        if (node == null) return;
        delete(node);
    }


    public void delete(RBNode<K, V> node) {
        //找到前驱节点或后驱节点替换当前节点数据
        if (leftOf(node) != null && rightOf(node) != null) {
            RBNode<K, V> t = successor(node);
            node.key = t.key;
            node.value = t.value;
            node = t;
        }

        //寻找子节点替换
        RBNode<K, V> replacement = node.left != null ? node.left : node.right;
        if (replacement != null) { //子节点不为空
            replacement.parent = node.parent;
            if (node.parent == null) {
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }
            node.parent = node.left = node.right = null;
            if (colorOf(node) == BLACK) {  //删除红色节点不会影响红黑树平衡
                fixAfterDelete(replacement);
            }
        } else if (node.parent == null) { //当前节点就是根节点
            root = null;
        } else {
            if (colorOf(node) == BLACK) {
                fixAfterDelete(node);
            }

            if(node.parent != null) {
                if (node == node.parent.left) {
                    node.parent.left = null;
                } else {
                    node.parent.right = null;
                }
                node.parent = null;
            }
        }
    }

    private void fixAfterDelete(RBNode<K, V> node) {
        while (node != root && colorOf(node) == BLACK) {
            if (node == leftOf(parentOf(node))) {
                RBNode<K, V> broNode = rightOf(parentOf(node));

                //判断此时是不是真正的兄弟节点，也就是要求兄弟节点也是黑色的，这里要对照2-3-4树去理解
                if (colorOf(broNode) == RED) {
                    setColor(parentOf(node), RED);
                    setColor(broNode, BLACK);
                    leftRotation(parentOf(node));
                    broNode = rightOf(parentOf(node));
                }

                //情况三：兄弟节点没的借,也就是兄弟节点的两个子节点都是空的 下面代码是等价的
                if (colorOf(leftOf(broNode)) == BLACK && colorOf(rightOf(broNode)) == BLACK) {
                    //兄弟节点没有子节点时，且兄弟节点为黑色时，需要把兄弟节点变为红色，父亲节点变为黑色
                    //为了保持红色树的平衡，需要迭代往上寻找一个兄弟节点本来就是红色的节点
                    //迭代最后找到的节点设置黑色
                    setColor(broNode, RED);
                    node = parentOf(node);

                } else {
                    //情况二：兄弟节点有的借,分两种情况，兄弟节点有一个子节点还是有两个子节点
                    //如果有两个子节点时，两个子节点都是可借的，只不过借左子节点时，需要先右旋一下，再左旋一下需要操作两步，借右子节点只需要左旋一步就可
                    //所以除了只有一个左子节点的情况，其他情况都只需要左旋一步即可

                    //兄弟节点右子节点为空，只有一个左子节点，需要先右旋一下，更换颜色
                    if (colorOf(rightOf(broNode)) == BLACK) {
                        setColor(leftOf(broNode), BLACK);
                        setColor(broNode, RED);
                        rightRotation(broNode);
                        broNode = rightOf(parentOf(node));
                    }

                    setColor(broNode, colorOf(parentOf(node)));
                    setColor(parentOf(node), BLACK);
                    setColor(rightOf(broNode), BLACK);
                    leftRotation(parentOf(node));

                    node = root; //处理完成，结束循环
                }
            } else {

                RBNode<K, V> broNode = leftOf(parentOf(node));

                //判断此时是不是真正的兄弟节点，也就是要求兄弟节点也是黑色的，这里要对照2-3-4树去理解
                if (colorOf(broNode) == RED) {
                    setColor(parentOf(node), RED);
                    setColor(broNode, BLACK);
                    rightRotation(parentOf(node));
                    broNode = leftOf(parentOf(node));
                }

                //情况三：兄弟节点没的借,也就是兄弟节点的两个子节点都是空的 下面代码是等价的
                if (colorOf(rightOf(broNode)) == BLACK && colorOf(leftOf(broNode)) == BLACK) {
                    //兄弟节点没有子节点时，且兄弟节点为黑色时，需要把兄弟节点变为红色，父亲节点变为黑色
                    //为了保持红色树的平衡，需要迭代往上寻找一个兄弟节点本来就是红色的节点
                    //迭代最后找到的节点设置黑色
                    setColor(broNode, RED);
                    node = parentOf(node);

                } else {
                    //情况二：兄弟节点有的借,分两种情况，兄弟节点有一个子节点还是有两个子节点
                    //如果有两个子节点时，两个子节点都是可借的，只不过借左子节点时，需要先右旋一下，再左旋一下需要操作两步，借右子节点只需要左旋一步就可
                    //所以除了只有一个左子节点的情况，其他情况都只需要左旋一步即可

                    //兄弟节点右子节点为空，只有一个左子节点，需要先右旋一下，更换颜色
                    if (colorOf(leftOf(broNode)) == BLACK) {
                        setColor(rightOf(broNode), BLACK);
                        setColor(broNode, RED);
                        leftRotation(broNode);
                        broNode = leftOf(parentOf(node));
                    }

                    setColor(broNode, colorOf(parentOf(node)));
                    setColor(parentOf(node), BLACK);
                    setColor(leftOf(broNode), BLACK);
                    rightRotation(parentOf(node));

                    node = root; //处理完成，结束循环
                }
            }
        }

        //情况一：替代节点是红色节点，则直接染黑，补偿删除的黑色节点，红黑树依旧保持平衡
        setColor(node, BLACK);
    }


    public RBNode<K, V> findNode(K key) {
        RBNode<K, V> node = root;
        while (node != null) {
            int cpt = key.compareTo(node.key);
            if (cpt > 0) {
                node = node.right;
            } else if (cpt < 0) {
                node = node.left;
            } else {
                return node;
            }
        }
        return null;
    }


    public RBNode<K, V> getRoot() {
        return root;
    }

    public void setRoot(RBNode<K, V> root) {
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
