package br.com.infoflavio.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import br.com.infoflavio.model.AnimalModel;
import br.com.infoflavio.model.FichaModel;
import br.com.infoflavio.vo.AnimalVO;
import br.com.infoflavio.vo.FichaVO;

@Transactional
public class FichaDAO  extends GenericDao<FichaModel> {
	
	private static FichaDAO instance;
	
	public static FichaDAO getInstance() {
		if (instance == null)
			instance = new FichaDAO();
		return instance;
	}
	
	public FichaVO buscarFicha(Integer id) throws Exception {		
		checkSession();
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<FichaModel> query = builder.createQuery(FichaModel.class);
		
		Root<FichaModel> ficha = query.from(FichaModel.class);
		
		TypedQuery<FichaModel> typedQuery = null;
		typedQuery = getEntityManager().createQuery(
		    query.select(ficha)
		    .where(builder.equal(ficha.get("id"), id))
		);
		FichaModel model = typedQuery.getSingleResult();
		
		FichaVO vo = new FichaVO();
		vo.setId(model.getId());
		vo.setDataCadastro(model.getDtCadastro());
		vo.setStatus(model.getStatus());
		vo.setObservacao(model.getObservacao());
		
		if (model.getAnimais() != null) {
			List<AnimalVO> animais = new ArrayList<AnimalVO>();
			
			for(AnimalModel animalModel : model.getAnimais()) {
				AnimalVO animalVO = new AnimalVO();
				animalVO.setId(animalModel.getId());
				animalVO.setNome(animalModel.getNome());				
				animais.add(animalVO);			
			}
			vo.setAnimais(animais);
		}
		
		return vo;
	}
	
	public List<FichaVO> listarFichas(Integer id, Date dataInicio, Date dataFim) throws Exception {		
		checkSession();
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<FichaModel> query = builder.createQuery(FichaModel.class);
		Root<FichaModel> ficha = query.from(FichaModel.class);
		
		Predicate predicate = builder.and();
		Boolean contemFiltro = Boolean.FALSE;
		
		if (id != null) {
			contemFiltro = Boolean.TRUE;
			predicate = builder.and(predicate, builder.equal(ficha.get("id"), id));
		}
		
		if (dataInicio != null && dataFim != null) {
			contemFiltro = Boolean.TRUE;
			predicate = builder.and(predicate, builder.between(ficha.<Date>get("dtCadastro"), dataInicio, dataFim));
		}
		
		TypedQuery<FichaModel> typedQuery = null;
		if(contemFiltro) {
			typedQuery = getEntityManager().createQuery(
				    query.select(ficha)
				    .where(predicate)
				);
		} else {
			typedQuery = getEntityManager().createQuery(query);
		}
		
		List<FichaModel> fichas = typedQuery.getResultList();
		
		List<FichaVO> listaRetorno = new ArrayList<FichaVO>();
		for(FichaModel model : fichas) {
			FichaVO vo = new FichaVO();
			vo.setId(model.getId());
			vo.setDataCadastro(model.getDtCadastro());
			vo.setObservacao(model.getObservacao());
			vo.setStatus(model.getStatus());
			
			listaRetorno.add(vo);			
		}
		return listaRetorno;
	}
	
	public Boolean cadastrarFicha(FichaVO vo) throws Exception {
		FichaModel entity = new FichaModel().create(vo.getDataCadastro(), vo.getStatus(), vo.getObservacao());
		entity.setAnimais(new ArrayList<AnimalModel>());		
		save(entity);	
		addAnimal(entity, vo);		
		return Boolean.TRUE;
	}
	
	public Boolean atualizarFicha(FichaVO vo) throws Exception {
		FichaModel entity = findById(vo.getId());
		entity.setDtCadastro(vo.getDataCadastro());
		entity.setStatus(vo.getStatus());
		entity.setObservacao(vo.getObservacao());
		update(entity);
		
		return Boolean.TRUE;
	}
	
	public Boolean deletarFicha(Integer id) throws Exception {
		FichaModel entity = findById(id);		
		this.removeAnimal(entity);		
		delete(entity);
		return Boolean.TRUE;
	}
	
	private void addAnimal(FichaModel fichaModel, FichaVO vo) throws Exception {
		if (!vo.getAnimais().isEmpty()) {
			for (AnimalVO animalVO : vo.getAnimais()) {
				AnimalModel animalModel = AnimalDAO.getInstance().findById(animalVO.getId());
				fichaModel.addAnimal(animalModel);
				animalModel.setFichaModel(fichaModel);
				AnimalDAO.getInstance().update(animalModel);
			}
		}
	}
	
	private void removeAnimal(FichaModel fichaModel) throws Exception {
		if(!fichaModel.getAnimais().isEmpty()) {
			for (AnimalModel animal : fichaModel.getAnimais()) {
				animal.setFichaModel(null);
				AnimalDAO.getInstance().update(animal);
			}
		}		
	}

}
