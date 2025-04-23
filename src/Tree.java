public class Tree {
    TreeNode root = null;
    Stack<TreeNode> iterationStack =  new Stack<TreeNode>();

    public Tree() {
    }

    public void initIteration()  {
        iterationStack.empty();
        findLeftmost( root );

        //traverse(root);
    }

    private void findLeftmost( TreeNode root ) {
        TreeNode current = root;
        if( current != null && current.left == null ) iterationStack.push( current );
        else {
            while (current.left != null) {
                iterationStack.push(current);
                current = current.left;
            }
            iterationStack.push(current);
        }
    }

//    private void traverse( TreeNode node ) {
//        if( node != null ) {
//            traverse(  node.right );
//            iterationStack.push( node );
//            traverse( node.left );
//        }
//    }

    public boolean hasNext() {
        return iterationStack.hasNext();
    }

    public Patient next() {
        TreeNode node = iterationStack.pop();

        if( node == null) return null;

        if( node.right != null ) {
            //node = node.right;
            findLeftmost( node.right );
        }

        return node.data;
    }

//    public Patient next() {
//        TreeNode next = iterationStack.pop();
//        if( next == null ) {
//            return null;
//        } else {
//            return next.data;
//        }
//    }

    public void add( Patient pat ) {
        TreeNode node = new TreeNode( pat );
        root = add( node, root );
    }

    public Patient find( PatientIdentity patID ) {
        TreeNode node;
        node = find( patID, root );
        if ( node != null && node.data != null) {
            return node.data;
        } else {
            return null;
        }
    }

    private TreeNode add( TreeNode node, TreeNode root ) {
        if( root == null ) {
            return node;
        } else if( node.data.getPatientIdentity().isLessThan( root.data.getPatientIdentity() )) {
                root.left = add( node, root.left );
        } else if( root.data.getPatientIdentity().isLessThan( node.data.getPatientIdentity() )) {
                root.right = add( node, root.right );
        }

        return root;
    }

    private TreeNode find( PatientIdentity patID, TreeNode root ) {
        if( root.data.getPatientIdentity().match( patID ) ) {
            return root;
        } else if( patID.isLessThan( root.data.getPatientIdentity() )) {
            return find( patID, root.left );
        } else if( root.data.getPatientIdentity().isLessThan( patID )) {
            return find( patID, root.right );
        }

        return null;
    }


    private class TreeNode {
        Patient data = null;
        TreeNode left, right;

        public TreeNode( Patient pat ) {
            data = pat;
        }
    }


}
