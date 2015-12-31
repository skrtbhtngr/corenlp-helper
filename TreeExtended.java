import java.util.Queue;
import java.util.LinkedList;
import edu.stanford.nlp.trees.Tree;

public abstract class TreeExtended extends edu.stanford.nlp.trees.Tree {

    public String treeToDot()
    {
        String result="graph  {\n";
        Queue<Tree> q = new LinkedList<>();
        q.add(this);
        int a, b;
        a=this.hashCode()*this.children().hashCode();
        result+=" N_"+(a<0?-a%Integer.MAX_VALUE:a)+" [label=\""+this.label()+"\"];\n";
        while(!q.isEmpty())
        {
            Tree t = q.remove();
            for(Tree child: t.children())
            {
                a=t.hashCode()*t.children().hashCode();
                if(child.children().length>0)
                    b=child.hashCode()*child.children().hashCode();
                else
                    b=child.hashCode()*this.hashCode();
                result+=" N_"+(b<0?-b%Integer.MAX_VALUE:b)+" [label=\""+child.label()+"\"];\n";
                result+=" N_"+(a<0?-a%Integer.MAX_VALUE:a)+" -- "+"N_"+(b<0?-b%Integer.MAX_VALUE:b)+";\n";
                q.add(child);
            }
        }
        result+="}";
        return result;
    }
}
