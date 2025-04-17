public class Tree {
    TreeNode root = null;
    Stack<TreeNode> iterationStack =  new Stack<TreeNode>();

    public Tree() {
    }

    public void initIteration()  {
        traverse(root);
    }

    private void traverse( TreeNode node ) {
        if( node != null ) {
            traverse(  node.right );
            iterationStack.push( node );
            traverse( node.left );
        }
    }

    public Patient next() {
        return iterationStack.pop().data;
    }

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
