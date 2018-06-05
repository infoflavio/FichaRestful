package br.com.infoflavio.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import br.com.infoflavio.model.AnimalModel;
import br.com.infoflavio.vo.AnimalVO;

@Transactional
public class AnimalDAO  extends GenericDao<AnimalModel> {
	
	private static AnimalDAO instance;
	
	public static AnimalDAO getInstance() {
		if (instance == null)
			instance = new AnimalDAO();
		return instance;
	}
	
	public List<AnimalVO> listarAnimais() throws Exception {		
		checkSession();
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<AnimalModel> query = builder.createQuery(AnimalModel.class);
		
		Root<AnimalModel> animal = query.from(AnimalModel.class);
		
		TypedQuery<AnimalModel> typedQuery = getEntityManager().createQuery(
		    query.select(animal)
		);
		
		List<AnimalModel> animais = typedQuery.getResultList();
		
		List<AnimalVO> listaRetorno = new ArrayList<AnimalVO>();
		for(AnimalModel model : animais) {
			AnimalVO vo = new AnimalVO();
			vo.setId(model.getId());
			vo.setNome(model.getNome());
			if(model.getFichaModel() != null){
				vo.setIdFicha(model.getFichaModel().getId());
			}
			if(model.getFichaModel() != null){
				vo.setIdFicha(model.getFichaModel().getId());
			}
			
			listaRetorno.add(vo);			
		}
		return listaRetorno;
	}
	
	public List<AnimalVO> listarAnimaisSemFicha() throws Exception {		
		List<AnimalVO> lista = listarAnimais();
		List<AnimalVO> listaRetorno = new ArrayList<AnimalVO>();
		for(AnimalVO vo : lista) {
			if (vo.getIdFicha() == null) {
				AnimalVO voRetorno = new AnimalVO();
				voRetorno.setId(vo.getId());
				voRetorno.setNome(vo.getNome());
				voRetorno.setSelecionado(Boolean.FALSE);
				
				listaRetorno.add(voRetorno);		
			}
		}
		return listaRetorno;
	}
	
	public Boolean atualizarAnimal(AnimalVO vo) throws Exception {
		AnimalModel entity = findById(vo.getId());
		entity.setNome(vo.getNome());
		
		update(entity);
		return Boolean.TRUE;
	}
	
	public Boolean deletarAnimal(Integer id) throws Exception {
		AnimalModel entity = findById(id);		
		delete(entity);
		return Boolean.TRUE;
	}

}
