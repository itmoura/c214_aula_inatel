package com.inatel.cdg;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inatel.cdg.entity.Periodo;
import com.inatel.cdg.entity.Professor;
import com.inatel.cdg.service.ProfessorService;
import com.inatel.cdg.service.impl.ProfessorServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.inatel.cdg.entity.Periodo.INTEGRAL;
import static com.inatel.cdg.entity.Periodo.NOTURNO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestProfessorService {
    ProfessorServiceImpl professorServiceImpl;

    public List<Professor> listProfessorJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String directory = System.getProperty("user.dir")+"/src/test/java/com/inatel/cdg/professor.json";
        List<Professor> professores = mapper.readValue(new File(directory), new TypeReference<List<Professor>>(){});

        return professores;
    }

    @Before
    public void setup() {
        professorServiceImpl = new ProfessorServiceImpl();
    }

//    casos felizes
    @Test
    public void testeCadastraProfessor(){
        var res = professorServiceImpl.cadastraProfessor(new Professor(1L, "Chris", "17:30", INTEGRAL));
        assertEquals(res, new Professor(1L, "Chris", "17:30", INTEGRAL));
    }

    @Test
    public void testeBuscaProfessor() throws IOException {
        var professores = listProfessorJson();
        var prof = professorServiceImpl.buscaProfessorId(1L,professores);
        assertEquals("Chris", prof.getNome());
        assertEquals(INTEGRAL, prof.getPeriodo());
    }

    @Test
    public void testeBuscaProfessorPorNome() throws IOException {
        var professores = listProfessorJson();
        var prof = professorServiceImpl.buscaProfessorNome("João",professores);
        assertEquals("João", prof.getNome());
        assertEquals(NOTURNO, prof.getPeriodo());
    }

//    casos tristes
    @Test
    public void testeBuscaProfessorError() throws IOException {
        var professores = listProfessorJson();
        var prof = professorServiceImpl.buscaProfessorId(4L,professores);
        assertNull(prof);
    }

    @Test
    public void testeBuscaProfessorPorNomeError() throws IOException {
        var professores = listProfessorJson();
        var prof = professorServiceImpl.buscaProfessorNome("Ronaldinho", professores);
        assertNull(prof);
    }

    @Test
    public void testeBuscaProfessorPorNome2Error() throws IOException {
        var professores = listProfessorJson();
        var prof = professorServiceImpl.buscaProfessorNome("Micheal Jackson", professores);
        assertNull(prof);
    }
}
