package com.gordon.binary.tree

import java.util.*
import kotlin.math.max

class BinaryTreeDemo {
    fun postTree(root: TreeNode): List<Int?> {
        val ans: MutableList<Int?> = ArrayList()
        val stack = LinkedList<TreeNode>()
        stack.push(root)
        while (!stack.isEmpty()) {
            val node = stack.pop()
            ans.add(node.`val`)
            if (node.left != null) {
                stack.push(node.left)
            }
            if (node.right != null) {
                stack.push(node.right)
            }
        }
        ans.reverse()
        return ans
    }

    /**
     * 二叉树的所有路径
     * 从根节点到叶子节点的路径 1->3->5
     */
    fun binaryTreePaths(root: TreeNode?): List<String> {
        val path = LinkedList<Int>()
        val ans = mutableListOf<String>()
        if (root == null) {
            return ans
        }
        traverse(root, path, ans)
        return ans;
    }

    private fun traverse(root: TreeNode, path: LinkedList<Int>, ans: MutableList<String>) {
        path.add(root.`val`)
        if (root.left == null && root.right == null) {
            val sb = StringBuilder()
            for (i in 0 until path.size - 1) {
                sb.append(path[i])
                sb.append("->")
            }
            sb.append(path[path.size - 1])
            ans.add(sb.toString())
            return
        }
        if (root.left != null) {
            traverse(root.left!!, path, ans)
            path.removeLast()
        }
        if (root.right != null) {
            traverse(root.right!!, path, ans)
            path.removeLast()
        }
    }

    fun isSymmetric(root: TreeNode?): Boolean {
        return compare(root, root)
    }

    private fun compare(left: TreeNode?, right: TreeNode?): Boolean {
        if (left == null && right == null) {
            return true
        } else if (left == null || right == null || left.`val` != right.`val`) {
            return false
        }
        return compare(left.left, right.right) && compare(left.right, right.left)
    }

    fun maxDepth(root: TreeNode?): Int {
        if (root == null) {
            return 0
        }
        val left = maxDepth(root.left)
        val right = maxDepth(root.right)
        return maxOf(left, right) + 1
    }

    fun minDepth(root: TreeNode?): Int {
        return getTreeHeight(root)
    }

    private fun getTreeHeight(root: TreeNode?): Int {
        if (root == null) {
            return 0
        }
        if (root.left == null && root.right != null) {
            return 1 + getTreeHeight(root.right)
        }
        if (root.left != null && root.right == null) {
            return 1 + getTreeHeight(root.left)
        }
        return 1 + minOf(getTreeHeight(root.left), getTreeHeight(root.right))
    }

    fun sumOfLeftLeaves(root: TreeNode?): Int {

        if (root == null) {
            return 0
        }
        if (root.left == null && root.right == null) {
            return 0
        }
        var left = sumOfLeftLeaves(root.left)
        if (root.left != null && root.left!!.left == null && root.left!!.right == null) {
            left = root.left!!.`val`
        }
        val right = sumOfLeftLeaves(root.right)
        return left + right
    }

    fun buildTree(inorder: IntArray, postOrder: IntArray): TreeNode? {
        val map = hashMapOf<Int, Int>()
        inorder.forEachIndexed { index, item ->
            map[item] = index
        }
        return buildTree(inorder, 0, inorder.size, postOrder, 0, postOrder.size, map)
    }

    private fun buildTree(
        inorder: IntArray, inBegin: Int, inEnd: Int, postOrder: IntArray,
        postBegin: Int, postEnd: Int, map: Map<Int, Int>
    ): TreeNode? {
        if (inBegin >= inEnd || postBegin >= postEnd) {
            return null
        }
        val index = map[postOrder[postEnd - 1]]
        val root = TreeNode(inorder[index!!])
        val leftOfLen = index - inBegin
        root.left = buildTree(inorder, inBegin, index, postOrder, postBegin, postBegin + leftOfLen, map)
        root.right = buildTree(inorder, index + 1, inEnd, postOrder, postBegin + leftOfLen, postEnd - 1, map)
        return root
    }
    fun searchBST(root: TreeNode?, `val`: Int): TreeNode? {
        if(root == null|| root.`val` ==`val`) return root
        return if(root.left!!.`val`<`val`){
            searchBST(root.right,`val`)
        }else{
            searchBST(root.left,`val`)
        }
    }
}