public class Tree {
    TreeNode root = null;

    public Tree() {
    }

    public void add( Patient pat ) {
        TreeNode node = new TreeNode( pat );
        root = add( node, root );
    }

    public Patient find( Patient pat ) {
        TreeNode node = new TreeNode( pat );
        node = find( node, root );
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

    private TreeNode find( TreeNode node, TreeNode root ) {
        if( root.data.getPatientIdentity().match( node.data.getPatientIdentity() ) ) {
            return node;
        } else if( node.data.getPatientIdentity().isLessThan( root.data.getPatientIdentity() )) {
            return find( node, root.left );
        } else if( root.data.getPatientIdentity().isLessThan( node.data.getPatientIdentity() )) {
            return find( node, root.right );
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
