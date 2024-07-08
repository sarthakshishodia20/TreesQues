import java.util.*;
public class Tree1 {
    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        public TreeNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class BinaryTree {
        static int index = -1;

        public static TreeNode buildTree(int[] nodes) {
            index++;
            if (nodes[index] == -1 || index >= nodes.length) {
                return null;
            }
            // index++;
            TreeNode newNode = new TreeNode(nodes[index]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);
            return newNode;
        }
    }

    public static void main(String[] args) {
        int[] nodes = { 1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1 };
        TreeNode root = BinaryTree.buildTree(nodes);
        // inOrder(root);
        // System.out.println();
        // System.out.println("level Order Traversal");
        // levelOrder(root);
        // System.out.println();
        // System.out.println("Height of Binary Tree");
        // System.out.println(height(root));
        // System.out.println("Count of Nodes");
        // System.out.println(countOfNodes(root));
        // System.out.println("Sum of Nodes");
        // System.out.println(sumOfNodes(root));
        // ArrayList<ArrayList<Integer>> ans=reverseLevelOrder(root);
        // for(int i=0;i<ans.size();i++){
        // ArrayList<Integer> ans0=ans.get(i);
        // for(int k=0;k<ans0.size();k++){
        // System.out.print(ans0.get(k)+" ");
        // }
        // System.out.println();
        // }
        ArrayList<ArrayList<Integer>> ans = zigzagLevelOrder(root);
        for (int i = 0; i < ans.size(); i++) {
            ArrayList<Integer> ans0 = ans.get(i);
            for (int k = 0; k < ans0.size(); k++) {
                System.out.print(ans0.get(k) + " ");
            }
            System.out.println();
        }

    }
    // public static TreeNode connect(TreeNode root){
    // if(root==null){
    // return null;
    // }
    // TreeNode leftMost=root;
    // while(leftMost.left!=null){
    // TreeNode current=leftMost;
    // while(current!=null){
    // current.left.next=current.right;
    // if(current.next!=null){
    // current.right.next=current.next.left;
    // }
    // current=current.next;
    // }

    // leftMost=leftMost.left;
    // }
    // return root;
    // }
    public static boolean isSCBT(TreeNode root){
     int index=0;
     int countNodes=countOfNodes(root);
     return iscbt(root,index,countNodes);
    }
    public static boolean iscbt(TreeNode root,int index,int countNodes){
        if(root==null){
            return true;
        }
        if(index>=countNodes){
            return false;
        }
        else{
            boolean left=iscbt(root.left, 2*index+1, countNodes);
            boolean right=iscbt(root.right, 2*index+2, countNodes);
            return left&&right;
        }
    
    }



    public static boolean isMaxOrder(TreeNode root){
        if(root==null){
            return true;
        }
        if(root.left==null && root.right==null){
            return true;

        }
        if(root.right==null){
            return root.data>root.left.data;
        }
        else{
            boolean left=isMaxOrder(root.left);
            boolean right=isMaxOrder(root.right);
            return (left&& right && (root.data>root.left.data && root.data>root.right.data));
        }
    }

    public static TreeNode removeLeafNodes(TreeNode root,int target){
        if(root==null){
            return null;
        }
        root.left=removeLeafNodes(root.left, target);
        root.right=removeLeafNodes(root.right, target);
        if(root.left==null && root.right==null && root.data==target){
            return null;
        }
        return root;
    }


    
    public static int maxLevelSum(TreeNode root)
    {
        Queue<TreeNode> q=new LinkedList<>();
        q.add(root);
        int maxLevel=Integer.MIN_VALUE;
        while(!q.isEmpty()){
            int n=q.size();
            int currentSum=0;
            for(int i=0;i<n;i++){
                TreeNode currNode=q.poll();
                currentSum+=currNode.data;
                if(currNode.left!=null){
                    q.add(currNode.left);
                }
                if(currNode.right!=null){
                    q.add(currNode.right);
                }
            }
            maxLevel=Math.max(maxLevel,currentSum);
        }
        return maxLevel;
    }

    public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        HashMap<String, Integer> map = new HashMap<>();
        List<TreeNode> list = new ArrayList<>();
        helper(root, list, map);
        return list;
    }


    
    public static String helper(TreeNode root, List<TreeNode> list, HashMap<String, Integer> map) {
        if (root == null) {
            return "#"; 
        }
        
        String str = "(";
        str += helper(root.left, list, map);
        str += Integer.toString(root.data);
        str += helper(root.right, list, map);
        str += ")";
        
        if (map.containsKey(str)) {
            if (map.get(str) == 1) {
                list.add(root);
            }
            map.put(str, map.get(str) + 1);
        } else {
            map.put(str, 1);
        }
        
        return str;
    }

    public boolean isUnivalTree(TreeNode root) {
        if(root==null){
            return true;
        }
        if(root.left!=null && root.left.data!=root.data){
            return false;
        }
        if(root.right!=null && root.right.data!=root.data){
            return false;
        }
        boolean leftAns=isUnivalTree(root.left);
        boolean rightAns=isUnivalTree(root.right);
        return leftAns&&rightAns;
    }
    
    public static ArrayList<Integer> diagonalPath(TreeNode root) {
        // Write your code here.
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        
        while (!q.isEmpty()) {
            TreeNode current = q.poll();
            
            while (current != null) {
                if (current.left != null) {
                    q.add(current.left);
                }
                list.add(current.data);
                current = current.right;
            }
        }
        
        return list;
    }
    
    
    public static List<Integer> maxValueBT(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if (root == null) {
            return list;
        }
        q.add(root);
        while (!q.isEmpty()) {
            int n = q.size();
            int maxValue = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                TreeNode currNode = q.poll();
                maxValue = Math.max(maxValue, currNode.data);
                if (currNode.left != null) {
                    q.add(currNode.left);
                }
                if (currNode.right != null) {
                    q.add(currNode.right);
                }
            }
            list.add(maxValue);
        }
        return list;
    }

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        TreeNode root = new TreeNode(root1.data + root2.data);
        root.left = mergeTrees(root1.left, root2.left);
        root.right = mergeTrees(root1.right, root2.right);
        return root;
    }

    public static void DFSusingSTACK(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        while (!s.isEmpty()) {
            TreeNode removed = s.pop();
            System.out.print(removed.data + " ");
            if (removed.right != null) {
                s.push(removed.right);
            }
            if (removed.left != null) {
                s.push(removed.left);
            }
        }
    }

    // Doubly Linked List;
    static TreeNode tail;

    public static TreeNode BTtoDLL(TreeNode root) {
        tail = null;
        return convertToDLL(root);
    }

    public static TreeNode convertToDLL(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode head = convertToDLL(root.left);
        if (tail == null) {
            head = root;
        } else {
            tail.right = root;
            root.left = tail;
        }
        tail = root;
        convertToDLL(root.right);
        return head;
    }

    public ArrayList<TreeNode> nodesAtOddLevels(TreeNode root) {
        ArrayList<TreeNode> list = new ArrayList<>();
        int level = 1;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int n = q.size();
            for (int i = 0; i < n; i++) {
                TreeNode currNode = q.poll();
                if (level % 2 != 0) {
                    list.add(currNode);
                }
                if (currNode.left != null) {
                    q.add(currNode.left);
                }
                if (currNode.right != null) {
                    q.add(currNode.right);
                }
            }
            level++;
        }
        return list;
    }

    public static void printKthLevel(TreeNode root, int k, int level) {
        if (root == null) {
            return;
        }
        if (level == k) {
            System.out.print(root.data + " ");
        }
        printKthLevel(root.left, k, level + 1);
        printKthLevel(root.right, k, level + 1);
    }

    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && root.data == targetSum) {
            return true;
        }
        return hasPathSum(root.left, targetSum - root.data) || hasPathSum(root.right, targetSum - root.data);
    }

    public static int sumRoottoLeaf(TreeNode root) {
        return helper(root, 0);
    }

    public static int helper(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        sum = sum * 10 + root.data;
        if (root.left == null && root.right == null) {
            return sum;
        }
        return helper(root.left, sum) + helper(root.right, sum);
    }

    public static boolean isPathExist(TreeNode root, int[] arr) {
        if (root == null) {
            return arr.length == 0;
        }
        return helperPath(root, arr, 0);
    }

    public static boolean helperPath(TreeNode root, int[] arr, int index) {
        if (root == null) {
            return false;
        }
        if (index >= arr.length || root.data != arr[index]) {
            return false;
        }
        if (root.left == null && root.right == null && index == arr.length - 1) {
            return true;
        }
        return helperPath(root.left, arr, index + 1) || helperPath(root.right, arr, index + 1);
    }

    public static List<String> binaryTreePath(TreeNode root) {
        List<String> list = new ArrayList<>();
        if (root != null) {
            binaryPath(root, "", list);
            return list;
        }
        return list;
    }

    public static void binaryPath(TreeNode root, String ans, List<String> path) {
        ans += root.data;
        if (root.left == null && root.right == null) {
            path.add(ans);
            return;
        }
        if (root.left != null) {
            binaryPath(root.left, ans + "->", path);
        }
        if (root.right != null) {
            binaryPath(root.right, ans + "->", path);
        }

    }

    public static boolean isOddEvenTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int level = 0;
        while (!q.isEmpty()) {
            int n = q.size();
            int prevValue = (level % 2 == 0) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                TreeNode currNode = q.poll();
                if (level % 2 == 0) {
                    if (currNode.data <= prevValue || currNode.data % 2 == 0) {
                        return false;
                    }
                } else {
                    if (currNode.data >= prevValue || currNode.data % 2 != 0) {
                        return false;
                    }
                }
                prevValue = currNode.data;
                if (currNode.left != null) {
                    q.add(currNode.left);
                }
                if (currNode.right != null) {
                    q.add(currNode.right);
                }
            }
            level++;
        }
        return true;
    }

    public static int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDiam = diameterOfBinaryTree(root.left);
        int rightDiam = diameterOfBinaryTree(root.right);
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        int selfDiam = leftHeight + rightHeight + 1;
        return Math.max(leftDiam, Math.max(rightDiam, selfDiam));
    }

    public static int unevenNodes(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }
        int leftheight = height(root.left);
        int rightHeight = height(root.right);
        int count = 0;
        if (Math.abs(leftheight - rightHeight) >= k) {
            count = 1;
        }
        count += unevenNodes(root.left, k);
        count += unevenNodes(root.right, k);
        return count;
    }

    public static ArrayList<Integer> InorderMorrisTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        TreeNode current = root;
        while (current != null) {
            if (current.left == null) {
                list.add(current.data);
                current = current.right;
            } else {
                TreeNode predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }
                if (predecessor.right == null) {
                    predecessor.right = current;
                    current = current.left;
                } else {
                    predecessor.right = null;
                    list.add(current.data);
                    current = current.right;
                }
            }
        }
        return list;
    }

    public static ArrayList<Integer> preorderMorris(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        TreeNode current = root;
        while (current != null) {
            if (current.left == null) {
                list.add(current.data);
                current = current.right;
            } else {
                TreeNode predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }
                if (predecessor.right == null) {
                    predecessor.right = current;
                    list.add(current.data);
                    current = current.left;
                } else {
                    predecessor.right = null;
                    // In case of inorder morris additon inside else and in case of preorder morris
                    // additon inside if statemenet
                    current = current.right;
                }
            }
        }
        return list;
    }

    public static List<Integer> postOrderMorris(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        // Postorder algo
        TreeNode current = root;
        while (current != null) {
            if (current.right == null) {
                list.add(current.data);
                current = current.left;
            } else {
                TreeNode predecessor = current.left;
                while (predecessor.left != null && predecessor.left != current) {
                    predecessor = predecessor.left;
                }
                if (predecessor.left == null) {
                    list.add(current.data);
                    predecessor.left = current;
                    current = current.right;
                } else {
                    predecessor.left = null;
                    current = current.left;
                }
            }
        }
        Collections.reverse(list);
        return list;
    }

    public static ArrayList<Integer> boundaryTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        list.add(root.data);
        traverseLeft(root.left, list);
        traverseLeaf(root, list);
        traverseRight(root.right, list);
        return list;
    }

    public static void traverseLeft(TreeNode root, ArrayList<Integer> list) {
        if (root == null || (isLeaf(root))) {
            return;
        }
        list.add(root.data);
        if (root.left != null) {
            traverseLeft(root.left, list);
        } else {
            traverseLeft(root.right, list);
        }
    }

    public static void traverseLeaf(TreeNode root, ArrayList<Integer> list) {
        if (root == null) {
            return;
        }
        if (isLeaf(root)) {
            list.add(root.data);
            return;
        }
        traverseLeaf(root.left, list);
        traverseLeaf(root.right, list);
    }

    public static void traverseRight(TreeNode root, ArrayList<Integer> list) {
        if (root == null || (isLeaf(root))) {
            return;

        }
        if (root.right != null) {
            traverseRight(root.right, list);
        } else {
            traverseRight(root.left, list);
        }
        list.add(root.data);
    }

    public static boolean isLeaf(TreeNode root) {
        return root.left == null && root.right == null;
    }

    public static TreeNode invertBinaryTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertBinaryTree(root.left);
        TreeNode right = invertBinaryTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    public static void flattenBinaryTree(TreeNode root) {
        TreeNode current = root;
        while (current != null) {
            if (current.left != null) {
                TreeNode temp = current.left;
                while (temp.right != null) {
                    temp = temp.right;
                }
                temp.right = current.right;
                current.right = current.left;
                current.left = null;
            }
            current = current.right;
        }
    }

    public static List<Integer> rightViewBinaryTree(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode currNode = q.poll();
                if (i == size - 1) {
                    list.add(currNode.data);
                }
                if (currNode.left != null) {
                    q.add(currNode.left);
                }
                if (currNode.right != null) {
                    q.add(currNode.right);
                }
            }
        }
        return list;
    }

    // static class Info {
    //     TreeNode node;
    //     int hd;

    //     public Info(TreeNode node, int hd) {
    //         this.node = node;
    //         this.hd = hd;
    //     }
    // }

    // public static ArrayList<Integer> topView(TreeNode root) {
    //     ArrayList<Integer> ans = new ArrayList<>();
    //     Queue<Info> q = new LinkedList<>();
    //     HashMap<Integer, TreeNode> map = new HashMap<>();
    //     q.add(new Info(root, 0));
    //     int min = 0;
    //     int max = 0;
    //     while (!q.isEmpty()) {
    //         Info curr = q.remove();
    //         if (!map.containsKey(curr.hd)) {
    //             map.put(curr.hd, curr.node);
    //         }
    //         if (curr.node.left != null) {
    //             q.add(new Info(curr.node.left, curr.hd - 1));
    //             min = Math.min(min, curr.hd - 1);
    //         }
    //         if (curr.node.right != null) {
    //             q.add(new Info(curr.node.right, curr.hd + 1));
    //             max = Math.max(max, curr.hd + 1);
    //         }
    //     }
    //     for (int i = min; i <= max; i++) {
    //         ans.add(map.get(i).data);
    //     }
    //     return ans;
    // }

    // public static ArrayList<Integer> bottomView(TreeNode root) {
    //     ArrayList<Integer> list = new ArrayList<>();
    //     Queue<Info> q = new LinkedList<>();
    //     HashMap<Integer, TreeNode> map = new HashMap<>();
    //     q.add(new Info(root, 0));
    //     int min = 0;
    //     int max = 0;
    //     while (!q.isEmpty()) {
    //         Info curr = q.poll();
    //         map.put(curr.hd, curr.node);
    //         if (curr.node.left != null) {
    //             q.add(new Info(curr.node.left, curr.hd - 1));
    //             min = Math.min(curr.hd - 1, min);
    //         }
    //         if (curr.node.right != null) {
    //             q.add(new Info(curr.node.right, curr.hd + 1));
    //             max = Math.max(curr.hd + 1, max);
    //         }
    //     }
    //     for (int i = min; i <= max; i++) {
    //         list.add(map.get(i).data);
    //     }
    //     return list;
    // }

    static TreeNode first=null;
    static TreeNode second=null;
    static TreeNode prev=null;

    public static TreeNode recoverBST(TreeNode root){
        if(root==null){
            return null;
        }
        inorder(root);
        if(first!=null && second!=null){
            int temp=first.data;
            first.data=second.data;
            second.data=temp;
        }
        return root;
    }
    public static void inorder(TreeNode root){
        if(root==null){
            return;
        }
        inorder(root.left);
        if(prev!=null && prev.data>=root.data){
            if(first==null){
                first=prev;
            }
            second=root;
        }
        prev=root;
        inorder(root.right);
    }


    public static ArrayList<Integer> leftView(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int n = q.size();
            TreeNode currNode = q.poll();
            for (int i = 0; i < n; i++) {
                if (i == 0) {
                    list.add(currNode.data);
                }
                if (currNode.left != null) {
                    q.add(currNode.left);
                }
                if (currNode.right != null) {
                    q.add(currNode.right);
                }
            }
        }
        return list;
    }

    public static ArrayList<Integer> rightView(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        if (root == null) {
            return list;
        }
        while (!q.isEmpty()) {
            int n = q.size();
            TreeNode currNode = q.poll();
            for (int i = 0; i < n; i++) {
                if (i == n - 1) {
                    list.add(currNode.data);
                }
                if (currNode.left != null) {
                    q.add(currNode.left);
                }
                if (currNode.right != null) {
                    q.add(currNode.right);
                }
            }
        }
        return list;
    }

    public static TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        ArrayList<TreeNode> path1 = new ArrayList<>();
        ArrayList<TreeNode> path2 = new ArrayList<>();

        getPath(root, path1, p);
        getPath(root, path2, q);

        int i = 0;
        for (; i < path1.size() && i < path2.size(); i++) {
            if (path1.get(i) != path2.get(i)) {
                break;
            }
        }
        return path1.get(i - 1);
    }

    public static boolean getPath(TreeNode root, ArrayList<TreeNode> path, TreeNode p) {
        if (root == null) {
            return false;

        }
        path.add(root);
        if (root == p) {
            return true;
        }
        boolean foundLeft = getPath(root.left, path, p);
        boolean foundRight = getPath(root.right, path, p);

        if (foundLeft || foundRight) {
            return true;
        }
        path.remove(path.size() - 1);
        return false;
    }

    public static TreeNode lca2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode leftLCA = lca2(root.left, p, q);
        TreeNode rightLCA = lca2(root.right, p, q);

        if (leftLCA == null) {
            return rightLCA;
        }
        if (rightLCA == null) {
            return leftLCA;
        }
        return root;
    }

    public static int minDistanceBetweenTwoNodes(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode lca = lca2(root, p, q);
        int minDistance1 = minDist(lca, p);
        int minDistance2 = minDist(lca, q);
        return minDistance1 + minDistance2;
    }

    public static int minDist(TreeNode root, TreeNode n) {
        if (root == null) {
            return -1;
        }
        if (root == n) {
            return 0;
        }
        int left = minDist(root.left, n);
        int right = minDist(root.right, n);

        if (left == -1 && right == -1) {
            return -1;
        } else if (left == -1) {
            return right;
        } else {
            return left;
        }
    }

    public static boolean isCousin(TreeNode root, int x, int y) {
        return (level(root, x, 0) == level(root, y, 0)) && (!isSibling(root, x, y));
    }

    public static int level(TreeNode root, int x, int level) {
        if (root == null) {
            return -1;
        }
        if (root.data == x) {
            return level;
        }
        int l = level(root.left, x, level + 1);
        if (l != -1) {
            return l;
        }
        return level(root.right, x, level + 1);
    }

    public static boolean isSibling(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }
        return (root.left != null && root.right != null && root.left.data == x && root.right.data == y)
                || (root.left != null && root.right != null && root.left.data == y && root.right.data == x)
                || (isSibling(root.left, x, y)) || (isSibling(root.right, x, y));
    }

    public static boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root.left);
        q.add(root.right);
        while (!q.isEmpty()) {
            TreeNode left = q.poll();
            TreeNode right = q.poll();
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null) {
                return false;
            }
            if (left.data != right.data) {
                return false;
            }
            q.add(left.left);
            q.add(right.right);
            q.add(left.right);
            q.add(right.left);
        }
        return true;
    }

    public static TreeNode findNextRight(TreeNode root, int key) {
        if (root == null) {
            return new TreeNode(-1);
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode currNode = q.poll();
                if (currNode.data == key) {
                    if (i == size - 1) {
                        return new TreeNode(-1);
                    } else {
                        return q.peek();
                    }
                }
                if (currNode.left != null) {
                    q.add(currNode.left);
                }
                if (currNode.right != null) {
                    q.add(currNode.right);
                }
            }
        }
        return new TreeNode(-1);
    }

    public static List<List<Integer>> zigZagTraverse(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> q = new LinkedList<>();
        boolean reverse = false;
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                if (!reverse) {
                    TreeNode currNode = q.pollLast();
                    list.add(currNode.data);
                    if (currNode.left != null) {
                        q.addFirst(currNode.left);
                    }
                    if (currNode.right != null) {
                        q.addFirst(currNode.left);
                    }
                } else {
                    TreeNode currNode = q.pollFirst();
                    list.add(currNode.data);
                    if (currNode.right != null) {
                        q.addLast(currNode.right);
                    }
                    if (currNode.left != null) {
                        q.addLast(currNode.left);
                    }
                }
            }
            reverse = !reverse;
            result.add(list);
        }
        return result;
    }

    public static void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data + " ");
    }

    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean left = isBalanced(root.left);
        boolean right = isBalanced(root.right);
        boolean diff = Math.abs(height(root.left) - height(root.right)) >= 1;
        if (left && right && diff) {
            return true;
        }
        return false;
    }

    public static TreeNode levelOrderSuccessor(TreeNode root, int key) {
        if (root == null) {
            return null;

        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode currNode = q.poll();
            if (currNode.left != null) {
                q.add(currNode.left);

            }
            if (currNode.right != null) {
                q.add(currNode.right);
            }
            if (currNode.data == key) {
                break;
            }
        }
        return q.peek();
    }

    public static ArrayList<Double> averageLevelOrder(TreeNode root) {
        ArrayList<Double> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            double averageLevel = 0;
            for (int i = 0; i < size; i++) {
                TreeNode currNode = q.poll();
                averageLevel += currNode.data;
                if (currNode.left != null) {
                    q.add(currNode.left);
                }
                if (currNode.right != null) {
                    q.add(currNode.right);
                }
            }
            averageLevel = averageLevel / size;
            ans.add(averageLevel);
        }
        return ans;
    }

    public static ArrayList<ArrayList<Integer>> reverseLevelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode currNode = q.poll();
                list.add(currNode.data);
                if (currNode.left != null) {
                    q.add(currNode.left);
                }
                if (currNode.right != null) {
                    q.add(currNode.right);
                }
            }
            result.add(list);
        }
        Collections.reverse(result);
        return result;
    }

    public static ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        boolean LtoR = true;
        if (root == null) {
            return result;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int n = q.size();
            ArrayList<Integer> list = new ArrayList<>(Collections.nCopies(n, 0));
            for (int i = 0; i < n; i++) {
                TreeNode currNode = q.poll();
                int index = LtoR ? i : n - i - 1;
                list.set(index, currNode.data);
                if (currNode.left != null) {
                    q.add(currNode.left);
                }
                if (currNode.right != null) {
                    q.add(currNode.right);
                }
            }
            LtoR = !LtoR;
            result.add(list);
        }
        return result;
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        Queue<TreeNode> qu = new LinkedList<>();
        qu.add(p);
        qu.add(q);

        while (!qu.isEmpty()) {
            TreeNode first = qu.remove();
            TreeNode second = qu.remove();
            if (first == null && second == null) {
                continue;
            } else if (first == null || second == null || first.data != second.data) {
                return false;
            }
            qu.add(first.left);
            qu.add(second.left);
            qu.add(first.right);
            qu.add(second.right);
        }
        return true;
    }

    public static int sumOfNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftSum = sumOfNodes(root.left);
        int rightSum = sumOfNodes(root.right);
        return leftSum + rightSum + root.data;
    }

    public static int countOfNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftCount = countOfNodes(root.left);
        int rightCount = countOfNodes(root.right);
        return leftCount + rightCount + 1;
    }

    public static int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = height(root.left);
        int right = height(root.right);
        return Math.max(left, right) + 1;
    }

    public static void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);

    }

    public static int getLevelDifference(TreeNode root) {
        if (root == null) {
            return -1;
        }
        int evenSum = 0;
        int oddSum = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int level = 0;
        while (!q.isEmpty()) {
            int n = q.size();
            for (int i = 0; i < n; i++) {
                TreeNode currNode = q.poll();
                if (level % 2 == 0) {
                    evenSum += currNode.data;
                } else {
                    oddSum += currNode.data;
                }
                if (currNode.left != null) {
                    q.add(currNode.left);
                }
                if (currNode.right != null) {
                    q.add(currNode.right);
                }
            }
            level++;
        }
        return evenSum - oddSum;
    }

    public static void levelOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        while (!q.isEmpty()) {
            TreeNode currNode = q.poll();
            if (currNode == null) {
                System.out.println();
                if (q.isEmpty()) {
                    break;
                } else {
                    q.add(null);
                }
            } else {
                System.out.print(currNode.data + " ");
                if (currNode.left != null) {
                    q.add(currNode.left);
                }
                if (currNode.right != null) {
                    q.add(currNode.right);
                }
            }
        }
    }
    public static boolean FlipEquivalent(TreeNode root1,TreeNode root2){
        if(root1==null && root2==null){
            return true;
        }
        if(root1==null || root2==null){
            return false;
        }
        if(root1.data!=root2.data){
            return false;
        }
        boolean a=FlipEquivalent(root1.left, root2.left) && FlipEquivalent(root1.right, root2.right);
        boolean b=FlipEquivalent(root1.left, root2.right) && FlipEquivalent(root1.right, root2.left);
        return a||b;
    }
}
