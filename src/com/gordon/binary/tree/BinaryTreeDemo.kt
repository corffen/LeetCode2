package com.gordon.binary.tree

import java.util.*

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
}